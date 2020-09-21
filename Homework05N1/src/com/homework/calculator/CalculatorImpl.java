package com.homework.calculator;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
        for (int i = number - 1; i > 1; --i) {
            number *= i;
        }
        return number == 0 ? 1 : number;
    }
}
