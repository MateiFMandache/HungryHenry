package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuActivity implements Activity {
    private final JFrame window;
    private JPanel pane;
    private final Controller controller;

    public StartMenuActivity(JFrame window, Controller controller) {
        this.window = window;
        this.controller = controller;
    }

    @Override
    public void enter() {
        pane = new JPanel();
        window.add(pane, BorderLayout.CENTER);
        pane.setBackground(Colors.BACKGROUND);
        JLabel title = new JLabel("Hungry Henry");
        JButton playButton = new JButton("Play");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> controller.invoke(new Command(CommandType.EXIT_GAME)));
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.add(title);
        pane.add(playButton);
        pane.add(exitButton);
    }

    @Override
    public void exit() {
        window.remove(pane);
    }
}
