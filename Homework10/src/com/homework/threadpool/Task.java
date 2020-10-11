package com.homework.threadpool;

public class Task implements Runnable {
    int number;

    Task(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Задача " + number + " начала выполняться.");
        try {
            Thread.sleep((long)(Math.random() * 9000 + 1000));
            System.out.println("Задача " + number + " выполнена.");
        } catch (InterruptedException e) {
            System.out.println("Выполнение задачи " + number + " прервано.");
            Thread.currentThread().interrupt();
        }
    }
}
