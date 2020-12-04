package com.mateifmandache.hungryhenry;

public abstract class Character {
    protected Position position;
    protected Velocity velocity;
    public Character(int row, int column) {
        position = Position.fromCell(row, column);
    }
    public void move(Wall wall) {
        updateVelocity();
        Position projectedPosition = position.applyVelocity(velocity);
        if (wall.isClear(projectedPosition, getRadius())) {
            position = projectedPosition;
        } else {
            switch (getBounceType()) {
                case STOP -> stop(wall);
            }
        }
    }
    private void stop(Wall wall) {
        // we compute the final position, which is then pushed by any adjacent walls
        position = position.applyVelocity(velocity);
        int row = position.getRow();
        int column = position.getColumn();
        double xInSquare = position.getXInSquare();
        double yInSquare = position.getYInSquare();
        double radius = getRadius();
        if (wall.isWall(row, column)) {
            System.out.println(
                    "Unhandled case in Character.stop: movement brings center onto wall");
        }
        // N, E, S, W walls
        if (wall.isWall(row, column-1) && xInSquare < radius) {
            xInSquare = radius;
        }
        if (wall.isWall(row-1, column) && yInSquare < radius) {
            yInSquare = radius;
        }
        if (wall.isWall(row, column+1) && Constants.SQUARE_SIZE - xInSquare < radius) {
            xInSquare = Constants.SQUARE_SIZE - radius;
        }
        if (wall.isWall(row+1, column) && Constants.SQUARE_SIZE - yInSquare < radius) {
            yInSquare = Constants.SQUARE_SIZE - radius;
        }
        // NW, NE, SW, SE Walls
        double distance;
        if (wall.isWall(row-1, column-1)
                && (distance = Math.sqrt(Math.pow(xInSquare, 2)
                                        + Math.pow(yInSquare, 2)))
                < radius) {
            xInSquare = xInSquare * radius / distance;
            yInSquare = yInSquare * radius / distance;
        }
        if (wall.isWall(row+1, column-1)
                && (distance = Math.sqrt(Math.pow(xInSquare, 2)
                            + Math.pow(Constants.SQUARE_SIZE - yInSquare, 2)))
                < radius) {
            xInSquare = xInSquare * radius / distance;
            yInSquare = Constants.SQUARE_SIZE
                        - (Constants.SQUARE_SIZE - yInSquare) * radius / distance;
        }
        if (wall.isWall(row-1, column+1)
                && (distance = Math.sqrt(Math.pow(Constants.SQUARE_SIZE - xInSquare, 2)
                                        + Math.pow(yInSquare, 2)))
                < radius) {
            xInSquare = Constants.SQUARE_SIZE
                        - (Constants.SQUARE_SIZE - xInSquare) * radius / distance;
            yInSquare = yInSquare * radius / distance;
        }
        if (wall.isWall(row+1, column+1)
                && (distance = Math.sqrt(Math.pow(Constants.SQUARE_SIZE - xInSquare, 2)
                                        + Math.pow(Constants.SQUARE_SIZE - yInSquare, 2)))
                < radius) {
            xInSquare = Constants.SQUARE_SIZE
                        - (Constants.SQUARE_SIZE - xInSquare) * radius / distance;
            yInSquare = Constants.SQUARE_SIZE
                        - (Constants.SQUARE_SIZE - yInSquare) * radius / distance;
        }
        position = Position.fromCell(row, column, xInSquare, yInSquare);
    }
    protected abstract BounceType getBounceType();
    protected abstract void updateVelocity();
    protected abstract double getRadius();
    public abstract Item toItem();
}
