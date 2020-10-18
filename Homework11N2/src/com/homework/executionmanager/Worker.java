package com.homework.executionmanager;

import java.util.Queue;

public class Worker extends Thread {
    private final Queue<Runnable> tasks;
    private final ThreadPool threadPool;

    public Worker(Queue<Runnable> tasks, ThreadPool threadPool) {
        this.tasks = tasks;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        Runnable task;
        while (!isInterrupted()) {
            try {
                synchronized (tasks) {
                    while (tasks.isEmpty() && !isInterrupted()) {
                        tasks.wait();
                    }
                    task = tasks.poll();
                }
                if (task != null) {
                    try {
                        task.run();
                        threadPool.increaseCompletedTaskCount();
                    }
                    catch (Throwable e) {
                        threadPool.increaseFailedTaskCount();
                    }
                }
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
