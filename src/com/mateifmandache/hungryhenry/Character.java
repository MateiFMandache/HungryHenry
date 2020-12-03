package com.mateifmandache.hungryhenry;

public abstract class Character {
    protected BounceType bounceType;
    protected Position position;
    protected Velocity velocity;
    public Character(int row, int column) {
        position = Position.fromCell(row, column);
    }
    public void move(Wall wall) {
        updateVelocity();
        Position projectedPosition = position.applyVelocity(velocity);
        if (wall.isClear(projectedPosition)) {
            position = projectedPosition;
        }
    }
    protected abstract void updateVelocity();
    protected abstract double getRadius();
    public abstract Item toItem();
}
