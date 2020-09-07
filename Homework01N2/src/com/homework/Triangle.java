package com.homework;

public class Triangle extends Polygon {
    Triangle(double side1, double side2, double side3) {
        sides = new double[3];
        sides[0] = side1;
        sides[1] = side2;
        sides[2] = side3;
    }
}
