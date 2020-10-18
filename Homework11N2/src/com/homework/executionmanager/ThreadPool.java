package com.homework.executionmanager;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool implements Context {
    private final List<Worker> workers;
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private volatile Runnable callback;
    private final AtomicInteger completedTaskCount = new AtomicInteger();
    private final AtomicInteger failedTaskCount = new AtomicInteger();
    private final AtomicInteger interruptedTaskCount = new AtomicInteger();
    private volatile int initialTaskCount;

    public ThreadPool(int threadCount) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (thread, throwable) -> {
        };
        workers = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; ++i) {
            Worker worker = new Worker(tasks, this);
            worker.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            workers.add(worker);
        }
    }

    public void start() {
        workers.forEach(Thread::start);
    }

    public Context execute(Runnable callback, Runnable... tasks) {
        synchronized (this.tasks) {
            this.tasks.addAll(Arrays.asList(tasks));
            initialTaskCount = this.tasks.size();
            this.tasks.notify();
        }
        this.callback = callback;
        return this;
    }

    public void increaseCompletedTaskCount() {
        completedTaskCount.incrementAndGet();
        startCallbackOrFinishingInterrupt();
    }

    public void increaseFailedTaskCount() {
        failedTaskCount.incrementAndGet();
        startCallbackOrFinishingInterrupt();
    }

    public void startCallbackOrFinishingInterrupt() {
        if (completedTaskCount.get() + failedTaskCount.get() == initialTaskCount && callback != null) {
            synchronized (tasks) {
                tasks.offer(callback);
                tasks.notify();
                callback = null;
            }
        } else if (isFinished()) {
            interrupt();
        }
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount.get();
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount.get();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount.get();
    }

    @Override
    public void interrupt() {
        workers.forEach(Thread::interrupt);
        synchronized (tasks) {
            interruptedTaskCount.set(tasks.size() + (callback == null ? 0 : 1));
            tasks.clear();
            callback = null;
        }
    }

    @Override
    public boolean isFinished() {
        return completedTaskCount.get() + failedTaskCount.get() == initialTaskCount + 1;
    }
}
