package com.mateifmandache.hungryhenry;

public abstract class Baddy extends Character {
    protected Henry henry;
    public Baddy(int row, int column) {
        super(row, column);
        henry = null;
    }
    public void setHenry(Henry henry) {
        this.henry = henry;
    }
    public boolean catches() {
        Position henryPosition = henry.getPosition();
        return Math.sqrt(Math.pow(henryPosition.getX() - position.getX(), 2)
                        + Math.pow(henryPosition.getY() - position.getY(), 2))
                < henry.getRadius() + getRadius();
    }
}
