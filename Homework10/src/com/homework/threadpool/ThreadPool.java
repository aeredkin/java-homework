package com.homework.threadpool;

public interface ThreadPool {
    void start();

    void execute(Runnable runnable);

    void stop();
}
