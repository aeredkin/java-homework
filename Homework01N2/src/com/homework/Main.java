package com.homework;

public class Main {

    public static void main(String[] args) {
        PlaneFigure[] figures = new PlaneFigure[4];
        figures[0] = new Circle(4.5);
        figures[1] = new Rect(6.4, 2.6);
        figures[2] = new Triangle(4.3, 6.5, 5.8);
        figures[3] = new Square(2.0);

        for (PlaneFigure figure: figures) {
            System.out.println(figure.perimeter());
        }
    }
}
