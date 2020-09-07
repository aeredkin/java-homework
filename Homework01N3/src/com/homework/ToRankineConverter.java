package com.homework;

public class ToRankineConverter implements Converter {
    @Override
    public double convert(double celsius) {
        return (celsius + 273.15) * 1.8;
    }
}
