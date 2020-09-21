package com.homework.calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CacheInvocationHandler implements InvocationHandler {
    private final Calculator calculator;
    private final HashMap<Object, Object> cache = new HashMap<>();

    CacheInvocationHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = cache.get(objects[0]);
        if (result == null) {
            result = method.invoke(calculator, objects);
            cache.put(objects[0], result);
        }
        return result;
    }
}
