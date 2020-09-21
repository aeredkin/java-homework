package com.homework.calculator;

public interface Calculator {
    /**
     * Расчёт факториала числа.
     * @param number аргумент
     * @return факториал аргумента
     */
    @Metric
    int calc(int number);
}
