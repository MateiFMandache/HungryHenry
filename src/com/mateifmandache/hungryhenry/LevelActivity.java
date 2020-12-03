package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class LevelActivity implements Activity {
    private final JLayeredPane window;
    private final Controller controller;
    private final Level level;
    private LevelModel levelModel;
    private JPanel pane;
    private LevelView view;
    public LevelActivity(JLayeredPane window, Controller controller, Level level) {
        this.window = window;
        this.controller = controller;
        this.level = level;
    }
    @Override
    public void enter() {
        pane = new JPanel();
        pane.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        levelModel = new LevelModel();
        levelModel.load(level);
        view = new LevelView(pane, controller);
        view.setup(levelModel);
        window.add(pane, Integer.valueOf(ZIndices.LevelActivity));
        (new java.util.Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, Constants.MILLISECONDS_PER_FRAME);
    }

    private void update() {
        levelModel.update();
        view.update(levelModel);
        (new java.util.Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, Constants.MILLISECONDS_PER_FRAME);
    }

    @Override
    public void exit() {
        window.remove(pane);
    }
}
