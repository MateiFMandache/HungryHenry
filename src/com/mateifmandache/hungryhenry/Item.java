package com.mateifmandache.hungryhenry;

public class Item {
    private final String type;
    private final double x;
    private final double y;
    private final double radius;
    public Item(double x, double y, double radius, String type) {
        // we convert center coordinates used by model to upper left coordinates for drawing
        this.radius = radius;
        this.x = x - radius;
        this.y = y - radius;
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getRadius() {
        return radius;
    }
}
