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
        return position.distanceTo(henry.getPosition()) < henry.getRadius() + getRadius();
    }
}
