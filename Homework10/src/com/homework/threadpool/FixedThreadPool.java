package com.homework.threadpool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final List<Worker> workers;
    private final Queue<Runnable> tasks = new ArrayDeque<>();

    public FixedThreadPool(int threadCount) {
        workers = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; ++i) {
            workers.add(new Worker(tasks));
        }
    }

    @Override
    public void start() {
        workers.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.offer(runnable);
            tasks.notify();
        }
    }

    @Override
    public void stop() {
        workers.forEach(Thread::interrupt);
    }
}
