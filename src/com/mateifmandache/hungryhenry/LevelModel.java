package com.mateifmandache.hungryhenry;

import java.util.ArrayList;
import java.util.List;

public class LevelModel {
    private double time;
    private int hunger;
    private boolean timed;
    private Wall wall;
    private Henry henry = null;
    public void load(Level data) {
        time = data.getTime();
        timed = data.getTimed();
        hunger = data.getHunger();
        wall = new Wall();
        for (int row = 0; row < Constants.LEVEL_HEIGHT; row++) {
            for (int column = 0; column < Constants.LEVEL_WIDTH; column++) {
                String objectString = data.getObject(row, column);
                // first two characters indicate type of object
                String typeCode = objectString.substring(0, 2);
                switch (typeCode) {
                    case StringCodes.HENRY:
                        henry = new Henry(row, column);
                        break;
                    case StringCodes.WALL:
                        wall.addWall(row, column);
                }
            }
        }
    }
    public void update() {
        henry.move(wall);
    }
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(wall.getItems());
        if (henry != null) {
            items.add(henry.toItem());
        }
        return items;
    }
    public int getTime() {
        return (int) time;
    }
    public int getHunger() {
        return hunger;
    }
    public boolean getTimed() {
        return timed;
    }
}
