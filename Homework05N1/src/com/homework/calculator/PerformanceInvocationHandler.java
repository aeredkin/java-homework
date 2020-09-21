package com.homework.calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class PerformanceInvocationHandler implements InvocationHandler {
    private final Calculator calculator;

    PerformanceInvocationHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            long before = System.nanoTime();
            Object result = method.invoke(calculator, objects);
            System.out.println("Время работы метода " + (System.nanoTime() - before) + " нс");
            return result;
        }
        return method.invoke(calculator, objects);
    }
}
