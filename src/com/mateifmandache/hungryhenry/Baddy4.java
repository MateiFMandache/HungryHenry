package com.mateifmandache.hungryhenry;

public class Baddy4 extends Baddy {
    private double speed = 1.0;
    public Baddy4(int row, int column) {
        super(row, column);
    }
    @Override
    protected BounceType getBounceType() {
        return BounceType.STOP;
    }

    @Override
    protected void updateVelocity() {
        if (henry != null) {
            Position henryPosition = henry.getPosition();
            double displacementX = henryPosition.getX() - position.getX();
            double displacementY = henryPosition.getY() - position.getY();
            double distance = Math.sqrt(Math.pow(displacementX, 2) + Math.pow(displacementY, 2));
            velocity = new Velocity(displacementX / distance, displacementY / distance);
        } else {
            velocity = new Velocity(0, 0);
        }
    }

    @Override
    protected double getRadius() {
        return 16.0;
    }

    @Override
    public Item toItem() {
        return new Item(position.getX(), position.getY(), getRadius(), StringCodes.BADDY4);
    }
}
