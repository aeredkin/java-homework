package com.homework.task;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result;
    private volatile CalltimeException calltimeException;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (calltimeException != null) {
            throw calltimeException;
        } else if (result == null) {
            synchronized (this) {
                if (calltimeException != null) {
                    throw calltimeException;
                } else if (result == null) {
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        calltimeException = new CalltimeException(e);
                        throw calltimeException;
                    }
                }
            }
        }
        return result;
    }
}
