package com.mateifmandache.hungryhenry;

import java.util.ArrayList;
import java.util.List;

public class Wall {
    private final double wallBlockRadius = Constants.SQUARE_SIZE / 2.0;
    private boolean[][] isWallArray;
    private List<Position> wallBlocks;
    public Wall() {
        isWallArray = new boolean[Constants.LEVEL_HEIGHT][Constants.LEVEL_WIDTH];
        initializeIsWall();
        wallBlocks = new ArrayList<>();
    }
    private void initializeIsWall() {
        for (int row = 0; row < Constants.LEVEL_HEIGHT; row++) {
            for (int column = 0; column < Constants.LEVEL_WIDTH; column++) {
                isWallArray[row][column] = false;
            }
        }
    }
    public void addWall(int row, int column) {
        wallBlocks.add(Position.fromCell(row, column));
        isWallArray[row][column] = true;
    }
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (Position blockPosition : wallBlocks) {
            items.add(new Item(blockPosition.getX(), blockPosition.getY(),
                                wallBlockRadius, StringCodes.WALL));
        }
        return items;
    }
    public boolean isWall(int row, int column) {
        try {
            return isWallArray[row][column];
        } catch (IndexOutOfBoundsException e) {
            // The space outside the play area is a solid wall
            return true;
        }
    }
    public boolean isClear(Position position, double radius) {
        int row = position.getRow();
        int column = position.getColumn();
        double xInSquare = position.getXInSquare();
        double yInSquare = position.getYInSquare();
        if (isWall(row, column)) {
            return false;
        }
        if (xInSquare < radius && isWall(row, column-1)) {
            return false;
        }
        if (yInSquare < radius && isWall(row-1, column)) {
            return false;
        }
        if (Constants.SQUARE_SIZE - xInSquare < radius && isWall(row, column+1)) {
            return false;
        }
        if (Constants.SQUARE_SIZE - yInSquare < radius && isWall(row + 1, column)) {
            return false;
        }
        if (isWall(row-1, column-1)
                && Math.sqrt(Math.pow(xInSquare, 2) + Math.pow(yInSquare, 2)) < radius) {
            return false;
        }
        if (isWall(row+1, column-1)
                && Math.sqrt(Math.pow(xInSquare, 2)
                            + Math.pow(Constants.SQUARE_SIZE - yInSquare, 2))
                < radius) {
            return false;
        }
        if (isWall(row-1, column+1)
                && Math.sqrt(Math.pow(Constants.SQUARE_SIZE - xInSquare, 2)
                            + Math.pow(yInSquare, 2))
                < radius) {
            return false;
        }
        if (isWall(row+1, column+1)
                && Math.sqrt(Math.pow(Constants.SQUARE_SIZE - xInSquare, 2)
                            + Math.pow(Constants.SQUARE_SIZE - yInSquare, 2))
                < radius) {
            return false;
        }
        return true;
    }
}
