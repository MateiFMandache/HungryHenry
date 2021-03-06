package com.mateifmandache.hungryhenry;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class LevelView {
    private final JPanel pane;
    private LevelPanel levelPanel;
    private final Controller controller;
    private JLabel hungerLabel;
    private JLabel timeLabel;
    public LevelView(JPanel pane, Controller controller) {
        this.pane = pane;
        this.controller = controller;
    }
    public void setup(LevelModel levelModel) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel infoBar = new JPanel();
        infoBar.setBackground(Colors.BACKGROUND);
        infoBar.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.MENU_BAR_HEIGHT));

        levelPanel = new LevelPanel(levelModel.getItems());
        levelPanel.setBackground(Colors.LEVEL_BACKGROUND);
        levelPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.PLAY_AREA_HEIGHT));

        infoBar.setLayout(new BoxLayout(infoBar, BoxLayout.LINE_AXIS));

        Consumer<JLabel> setupLabel = label -> {
            label.setForeground(Colors.DARK);
            label.setFont(new Font(Constants.FONT, Font.PLAIN, 36));
            label.setAlignmentY(Component.CENTER_ALIGNMENT);
        };

        hungerLabel = new JLabel(String.format("Hunger: %d", levelModel.getHunger()));
        setupLabel.accept(hungerLabel);

        timeLabel = new JLabel(String.format("Time: %d", levelModel.getTime()));
        setupLabel.accept(timeLabel);
        timeLabel.setVisible(levelModel.getTimed());

        MyButton menuButton = new MyButton("Menu");
        menuButton.setThickness(4);
        menuButton.setRadius(15);
        menuButton.setForeground(Colors.DARK);
        menuButton.setContentAreaFilled(false);
        menuButton.setFont(new Font(Constants.FONT, Font.PLAIN, 24));
        menuButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuButton.addActionListener(e -> {

        });

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
    public void update(LevelModel levelModel) {
        hungerLabel.setText(String.format("Hunger: %d", levelModel.getHunger()));
        timeLabel.setText(String.format("Time: %d", levelModel.getTime()));
        levelPanel.setItems(levelModel.getItems());
        levelPanel.setCountDown(levelModel.getCountDown());
        levelPanel.setCountDownString(levelModel.getCountDownTime());
        levelPanel.repaint();
    }
    private static class LevelPanel extends JPanel {
        private java.util.List<Item> items;
        private boolean countDown;
        private String countDownString;
        private Map<String, BufferedImage> foodImages = new HashMap<>();

        public LevelPanel(java.util.List<Item> items) {
            super();
            this.items = items;
            loadFoodImages();
        }

        private void loadFoodImages() {
            for (Map.Entry<String, String> foodName : FoodDictionaries.names.entrySet()) {
                BufferedImage foodImage = null;
                try {
                    foodImage = ImageIO.read(new File(
                            "./res/images/pngs/" + foodName.getValue() + ".png"));
                } catch (IOException e) {
                    System.out.println("Failed to load image: " + foodName.getValue());
                }
                foodImages.put(foodName.getKey(), foodImage);
            }
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public void setCountDown(boolean countDown) {
            this.countDown = countDown;
        }

        public void setCountDownString(String countDownString) {
            this.countDownString = countDownString;
        }

        private void paintBaddy(Graphics2D g2, double x, double y, double radius, Color color) {
            g2.setColor(color);
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y,2*radius, 2*radius);
            g2.fill(circle);
        }

        @Override
        public void paintComponent(Graphics g) {
            Toolkit.getDefaultToolkit().sync();
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            for (Item item : items) {
                String itemType = item.getType();
                switch (itemType) {
                    case StringCodes.HENRY:
                        g2.setColor(Colors.LIGHT);
                        Ellipse2D.Double circle = new Ellipse2D.Double(
                                item.getX(), item.getY(),
                                2*item.getRadius(), 2*item.getRadius());
                        g2.fill(circle);
                        break;
                    case StringCodes.WALL:
                        g2.setColor(Colors.LEVEL_WALL);
                        Rectangle2D.Double rect = new Rectangle2D.Double(
                                item.getX(), item.getY(),
                                2*item.getRadius(), 2*item.getRadius());
                        g2.fill(rect);
                        break;
                    case StringCodes.BADDY4:
                        paintBaddy(g2, item.getX(), item.getY(), item.getRadius(), Color.GREEN);
                        break;
                    case StringCodes.FOOD1:
                    case StringCodes.FOOD2:
                    case StringCodes.FOOD3:
                    case StringCodes.FOOD4:
                    case StringCodes.FOOD5:
                    case StringCodes.FOOD6:
                    case StringCodes.FOOD7:
                    case StringCodes.FOOD8:
                    case StringCodes.FOOD9:
                        g2.drawImage(foodImages.get(itemType),
                                    (int) Math.round(item.getX()),
                                    (int) Math.round(item.getY()),
                                    null);
                        break;
                }
            }
            if (countDown) {
                g2.setColor(Colors.LIGHT);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setFont(new Font(Constants.FONT, Font.PLAIN, 72));
                g2.drawString(countDownString, Constants.WINDOW_WIDTH / 2 - 24, // fudge factors
                                                Constants.PLAY_AREA_HEIGHT / 2 + 18);
            }
        }
    }
}
