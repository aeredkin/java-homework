package com.homework;

public class ToFahrenheitConverter implements Converter {
    @Override
    public double convert(double celsius) {
        return celsius * 1.8 + 32.0;
    }
}
