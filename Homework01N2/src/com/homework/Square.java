package com.homework;

public class Square extends Rect {
    Square(double side) {
        super(side, side);
    }

    @Override
    public double perimeter() {
        return 4.0 * sides[0];
    }
}
