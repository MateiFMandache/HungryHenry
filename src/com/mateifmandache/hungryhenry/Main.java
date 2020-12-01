package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Hungry Henry");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Controller controller = new Controller(window);
        controller.start();
        Dimension windowSize = new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setPreferredSize(windowSize);
        window.pack();
        window.setVisible(true);
    }
}
