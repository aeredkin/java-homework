package ru.sbt.test.refactoring;

public class Field {
    private final int width;
    private final int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
