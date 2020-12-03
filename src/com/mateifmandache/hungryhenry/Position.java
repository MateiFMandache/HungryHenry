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
        return new Position((column + 1.0/2.0) * Constants.SQUARE_SIZE,
                            (row + 1.0/2.0) * Constants.SQUARE_SIZE);
    }
}
