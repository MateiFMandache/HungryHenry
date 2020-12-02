package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // set up a layered pane inside the root frame
        JFrame frame = new JFrame("Hungry Henry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane window = new JLayeredPane();
        window.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        frame.add(window, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        Controller controller = new Controller(window, frame);
        controller.start();
    }
}
