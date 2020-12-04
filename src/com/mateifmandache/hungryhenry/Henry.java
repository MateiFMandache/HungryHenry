package com.mateifmandache.hungryhenry;

public class Henry extends Character {
    private final double speed = 3.0;
    private final KeyPressListener keyPressListener;
    public Henry(int row, int column) {
        super(row, column);
        keyPressListener = new KeyPressListener();
    }

    @Override
    protected void updateVelocity() {
        double horizontalDirection = keyPressListener.getHorizontalDirection();
        double verticalDirection = keyPressListener.getVerticalDirection();
        // Normalize diagonal motions
        if (horizontalDirection != 0.0 && verticalDirection != 0.0) {
            horizontalDirection /= Math.sqrt(2.0);
            verticalDirection /= Math.sqrt(2.0);
        }
        velocity = new Velocity(horizontalDirection * speed,
                                verticalDirection * speed);
    }

    @Override
    protected BounceType getBounceType() {
        return BounceType.STOP;
    }

    @Override
    public double getRadius() {
        return 16.0;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public Item toItem() {
        return new Item(position.getX(), position.getY(), getRadius(), StringCodes.HENRY);
    }
}
