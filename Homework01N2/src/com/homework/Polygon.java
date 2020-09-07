package com.homework;

public abstract class Polygon implements PlaneFigure {
    protected double[] sides;

    @Override
    public double perimeter() {
        double sum = 0.0;
        for (double side : sides) {
            sum += side;
        }
        return sum;
    }
}
