package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;

public class LevelActivity implements Activity {
    private JLayeredPane window;
    private Controller controller;
    private JPanel pane;
    private LevelView view;
    public LevelActivity(JLayeredPane window, Controller controller) {
        this.window = window;
        this.controller = controller;
    }
    @Override
    public void enter() {
        pane = new JPanel();
        pane.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        view = new LevelView(pane);
        view.setup();
        window.add(pane, Integer.valueOf(ZIndices.LevelActivity));
    }

    @Override
    public void exit() {
        window.remove(pane);
    }
}
