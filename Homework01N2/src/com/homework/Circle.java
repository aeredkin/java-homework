package com.homework;

public class Circle implements PlaneFigure {
    protected double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2.0 * Math.PI * radius;
    }
}
