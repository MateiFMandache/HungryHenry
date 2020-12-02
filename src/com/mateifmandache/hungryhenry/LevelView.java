package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class LevelView {
    private final JPanel pane;
    private LevelPanel levelPanel;
    public LevelView(JPanel pane) {
        this.pane = pane;
    }
    public void setup() {
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel infoBar = new JPanel();
        infoBar.setBackground(Colors.BACKGROUND);
        infoBar.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.MENU_BAR_HEIGHT));

        levelPanel = new LevelPanel();
        levelPanel.setBackground(Colors.LEVEL_BACKGROUND);
        levelPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH,
                                    Constants.LEVEL_HEIGHT * Constants.SQUARE_SIZE));

        infoBar.setLayout(new BoxLayout(infoBar, BoxLayout.LINE_AXIS));

        Consumer<JLabel> setupLabel = label -> {
            label.setForeground(Colors.DARK);
            label.setFont(new Font(Constants.FONT, Font.PLAIN, 36));
            label.setAlignmentY(Component.CENTER_ALIGNMENT);
        };

        JLabel hungerLabel = new JLabel("Hunger: 1");
        setupLabel.accept(hungerLabel);

        JLabel timeLabel = new JLabel("Time: 60");
        setupLabel.accept(timeLabel);

        MyButton menuButton = new MyButton("Menu");
        menuButton.setThickness(4);
        menuButton.setRadius(15);
        menuButton.setForeground(Colors.DARK);
        menuButton.setContentAreaFilled(false);
        menuButton.setFont(new Font(Constants.FONT, Font.PLAIN, 24));
        menuButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        infoBar.add(Box.createRigidArea(new Dimension(10, 0)));
        infoBar.add(hungerLabel);
        infoBar.add(Box.createHorizontalGlue());
        infoBar.add(timeLabel);
        infoBar.add(Box.createHorizontalGlue());
        infoBar.add(menuButton);
        infoBar.add(Box.createRigidArea(new Dimension(10, 0)));

        pane.add(infoBar);
        pane.add(levelPanel);
    }
    private static class LevelPanel extends JPanel {

    }
}
