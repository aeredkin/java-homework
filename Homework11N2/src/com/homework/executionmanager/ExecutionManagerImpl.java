package com.homework.executionmanager;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ThreadPool threadPool = new ThreadPool(tasks.length > 4 ? 4 : tasks.length);
        threadPool.start();
        return threadPool.execute(callback, tasks);
    }
}
