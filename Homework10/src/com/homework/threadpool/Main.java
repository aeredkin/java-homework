package com.homework.threadpool;

public class Main {

    public static void main(String[] args) {
	    ThreadPool threadPool = new FixedThreadPool(2);
	    threadPool.start();

	    threadPool.execute(new Task(1));
        threadPool.execute(new Task(2));
        threadPool.execute(new Task(3));
        threadPool.execute(new Task(4));

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        threadPool.stop();

        threadPool = new ScalableThreadPool(2, 4);
        threadPool.start();

        threadPool.execute(new Task(5));
        threadPool.execute(new Task(6));
        threadPool.execute(new Task(7));
        threadPool.execute(new Task(8));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        threadPool.execute(new Task(9));
        threadPool.execute(new Task(10));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        threadPool.stop();
    }
}
