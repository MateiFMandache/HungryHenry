package com.mateifmandache.hungryhenry;

import java.util.List;

public class Food {
    private String stringCode;
    // position of center
    private Position position;
    // points around the edge to check for being eaten
    private List<Position> catchPoints;
    // amount of hunger decrease
    private int nutrition;
    // radius of food is always 16
    private double radius = 16.0;
    public Food(String stringCode, Position position, List<Position> catchPoints, int nutrition) {
        this.stringCode = stringCode;
        this.position = position;
        this.catchPoints = catchPoints;
        this.nutrition = nutrition;
    }

    public boolean catches(Henry henry) {
        Position henryPosition = henry.getPosition();
        double henryRadius = henry.getRadius();
        // initial check if henry is anywhere near the food
        if (position.distanceTo(henryPosition) > Math.sqrt(2) * radius + henryRadius) {
            return false;
        }
        // check each catch point to see if henry is over it
        for (Position catchPoint : catchPoints) {
            if (catchPoint.distanceTo(henryPosition) < henryRadius) {
                return true;
            }
        }
        // no catch point is under henry
        return false;
    }

    public int getNutrition() {
        return nutrition;
    }

    public Item toItem() {
        return new Item(position.getX(), position.getY(), radius, stringCode);
    }
}
