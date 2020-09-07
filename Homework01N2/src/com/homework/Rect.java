package com.homework;

public class Rect extends Polygon {
    Rect(double side1, double side2) {
        sides = new double[4];
        sides[0] = side1;
        sides[1] = side2;
        sides[2] = side1;
        sides[3] = side2;
    }

    @Override
    public double perimeter() {
        return 2.0 * (sides[0] + sides[1]);
    }
}
