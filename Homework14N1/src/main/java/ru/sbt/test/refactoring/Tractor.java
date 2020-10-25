package ru.sbt.test.refactoring;

public class Tractor {
    Position position = new Position(0, 0);
    Field field = new Field(5, 5);
    private Orientation orientation = Orientation.NORTH;

    public void move(String command) {
        if (command.equals("F")) {
            moveForwards();
        } else if (command.equals("T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        position.changePosition(orientation);
        if (position.getX() > field.getWidth() || position.getY() > field.getHeight()) {
            throw new TractorInDitchException("Tractor in ditch. Position " + position.getX() + " " + position.getY());
        }
    }

    public void turnClockwise() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
