package com.homework.calculator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        System.out.println("Методы:");
        printAllMethods(CalculatorImpl.class);

        System.out.println("Getters:");
        printGetters(CalculatorImpl.class);

        if (constantsCheck(CalculatorImpl.class)) {
            System.out.println("Значения всех текстовых констант совпадают с их именами.");
        } else {
            System.out.println("Значения не всех текстовых констант совпадают с их именами.");
        }

        Calculator cacheProxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(), new CacheInvocationHandler(new CalculatorImpl()));
        System.out.println(cacheProxy.calc(10));
        System.out.println(cacheProxy.calc(9));
        System.out.println(cacheProxy.calc(10));
        System.out.println(cacheProxy.calc(9));

        Calculator performanceProxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(), new PerformanceInvocationHandler(new CalculatorImpl()));
        System.out.println(performanceProxy.calc(8));
        System.out.println(performanceProxy.calc(7));
    }

    public static void printAllMethods(Class<?> c) {
        String className = c.getSimpleName();
        for (Method method : c.getDeclaredMethods()) {
            System.out.println(className + "." + method.getName());
        }
        if (c.getSuperclass() != null) {
            printAllMethods(c.getSuperclass());
        }
    }

    public static void printGetters(Class<?> c) {
        for (Method method : c.getMethods()) {
            if (method.getName().startsWith("get") && method.getReturnType() != void.class) {
                System.out.println(method.getName());
            }
        }
    }

    public static boolean constantsCheck(Class<?> c) {
        for (Field field : c.getDeclaredFields()) {
            if (field.getType() == String.class && Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                try {
                    if (field.getName() != field.get(c)) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
