package ru.sbt.test.refactoring;

public class Position {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void changePosition(Orientation orientation) {
        switch (orientation) {
            case NORTH:
                ++y;
                break;
            case EAST:
                ++x;
                break;
            case SOUTH:
                --y;
                break;
            case WEST:
                --x;
                break;
        }
    }
}
