package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class StartMenuActivity implements Activity {
    private final JLayeredPane window;
    private JPanel pane;
    private final Controller controller;

    public StartMenuActivity(JLayeredPane window, Controller controller) {
        this.window = window;
        this.controller = controller;
    }

    @Override
    public void enter() {
        pane = new JPanel();
        pane.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        pane.setBackground(Colors.BACKGROUND);

        Consumer<MyButton> setUpButton = button -> {
            button.setRadius(25);
            button.setThickness(6);
            button.setForeground(Colors.DARK);
            button.setContentAreaFilled(false);
            button.setFont(new Font(Constants.FONT, Font.PLAIN, 36));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        };

        JLabel title = new JLabel("Hungry Henry");
        title.setForeground(Colors.LIGHT);
        title.setFont(new Font(Constants.FONT, Font.PLAIN, 96));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        MyButton playButton = new MyButton("Play");
        setUpButton.accept(playButton);
        playButton.addActionListener(e -> controller.invoke(new LevelsetCommand("test")));

        MyButton exitButton = new MyButton("Exit");
        setUpButton.accept(exitButton);
        exitButton.addActionListener(e -> controller.invoke(new Command(CommandType.EXIT_GAME)));

        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        pane.add(Box.createVerticalGlue());
        pane.add(title);
        pane.add(Box.createRigidArea(new Dimension(0, 5)));
        pane.add(playButton);
        pane.add(Box.createRigidArea(new Dimension(0, 5)));
        pane.add(exitButton);
        pane.add(Box.createVerticalGlue());
        window.add(pane, Integer.valueOf(ZIndices.StartMenuActivity));
    }

    @Override
    public void exit() {
        window.remove(pane);
    }
}
