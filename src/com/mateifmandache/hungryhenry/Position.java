package com.mateifmandache.hungryhenry;

public class Position {
    private double x;
    private double y;
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getX() {
        return this.x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getY() {
        return this.y;
    }
    public Position applyVelocity(Velocity v) {
        return new Position(x + v.getX() * Constants.VELOCITY_MULTIPLIER,
                            y + v.getY() * Constants.VELOCITY_MULTIPLIER);
    }
    public static Position fromCell(int row, int column) {
        // start position is in the center of the cell
        return new Position((column + 1.0/2.0) * Constants.SQUARE_SIZE,
                            (row + 1.0/2.0) * Constants.SQUARE_SIZE);
    }
    public static Position fromCell(int row, int column, double xInCell, double yInCell) {
        return new Position(column* Constants.SQUARE_SIZE + xInCell,
                            row * Constants.SQUARE_SIZE + yInCell);
    }
    public int getRow() {
        return (int) (y / Constants.SQUARE_SIZE);
    }
    public int getColumn() {
        return (int) (x / Constants.SQUARE_SIZE);
    }
    public double getXInSquare() {
        return x % Constants.SQUARE_SIZE;
    }
    public double getYInSquare() {
        return y % Constants.SQUARE_SIZE;
    }
}
