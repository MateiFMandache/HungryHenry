package com.mateifmandache.hungryhenry;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyPressListener {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    public KeyPressListener() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> upPressed = true;
                        case KeyEvent.VK_DOWN -> downPressed = true;
                        case KeyEvent.VK_LEFT -> leftPressed = true;
                        case KeyEvent.VK_RIGHT -> rightPressed = true;
                    }
                    break;
                case KeyEvent.KEY_RELEASED:
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> upPressed = false;
                        case KeyEvent.VK_DOWN -> downPressed = false;
                        case KeyEvent.VK_LEFT -> leftPressed = false;
                        case KeyEvent.VK_RIGHT -> rightPressed = false;
                    }
            }
            return true;
        });
    }
    public double getHorizontalDirection() {
        double horizontalDirection = 0.0;
        if (leftPressed) {
            horizontalDirection -= 1.0;
        }
        if (rightPressed) {
            horizontalDirection += 1.0;
        }
        return horizontalDirection;
    }
    public double getVerticalDirection() {
        double verticalDirection = 0.0;
        if (upPressed) {
            verticalDirection -= 1.0;
        }
        if (downPressed) {
            verticalDirection += 1.0;
        }
        return verticalDirection;
    }
}
