package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private int radius = 0;
    private int thickness = 1;
    public MyButton(String text) {
        super(text);
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(thickness));
        int inset = thickness / 2;
        g2.drawRoundRect(inset, inset, getWidth()-1-2*inset,
                         getHeight()-1-2*inset, radius, radius);
    }
}
