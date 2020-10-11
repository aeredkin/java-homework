package com.homework.threadpool;

import java.util.Queue;

public class Worker extends Thread {
    private final Queue<Runnable> tasks;

    public Worker(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " запущен.");
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
                    task.run();
                }
            } catch (InterruptedException e) {
                interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " остановлен.");
    }
}
