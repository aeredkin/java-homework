package com.homework.threadpool;

import java.util.*;

public class ScalableThreadPool implements ThreadPool {
    private final int minThreadCount;
    private final int maxThreadCount;
    private final List<Worker> workers;
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Thread threadReducer;

    public ScalableThreadPool(int minThreadCount, int maxThreadCount) {
        this.minThreadCount = minThreadCount;
        this.maxThreadCount = maxThreadCount;
        workers = new ArrayList<>(maxThreadCount);
        for (int i = 0; i < minThreadCount; ++i) {
            workers.add(new Worker(tasks));
        }
        threadReducer = new Thread(this::run);
        threadReducer.start();
    }

    @Override
    public void start() {
        synchronized (workers) {
            workers.forEach(Thread::start);
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (workers) {
            synchronized (tasks) {
                if (workers.size() == maxThreadCount || tasks.isEmpty()) {
                    tasks.offer(runnable);
                    tasks.notify();
                } else {
                    tasks.offer(runnable);
                    Worker worker = new Worker(tasks);
                    worker.start();
                    workers.add(worker);
                    workers.notify();
                }
            }
        }
    }

    @Override
    public void stop() {
        threadReducer.interrupt();
        synchronized (workers) {
            workers.forEach(Thread::interrupt);
        }
    }

    private void run() {
        while (!threadReducer.isInterrupted()) {
            try {
                synchronized (workers) {
                    synchronized (tasks) {
                        if (tasks.isEmpty() && workers.size() > minThreadCount) {
                            for (Iterator<Worker> workerIterator = workers.iterator();
                                 workerIterator.hasNext() && workers.size() > minThreadCount; ) {
                                Worker worker = workerIterator.next();
                                if (worker.getState() == Thread.State.WAITING) {
                                    worker.interrupt();
                                    workerIterator.remove();
                                }
                            }
                        }
                    }
                    if (workers.size() == minThreadCount) {
                        workers.wait();
                    } else {
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                threadReducer.interrupt();
            }
        }
    }
}
