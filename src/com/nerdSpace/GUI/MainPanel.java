package com.nerdSpace.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MainPanel extends JPanel {

    int load_x, load_y;
    float gradient_pos;
    long speed;

    public MainPanel(int x, int y){
        this.load_x = x/2;
        this.load_y = y/2;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point2D center = new Point2D.Float(load_x, load_y);
        float radius = 150;
        float[] dist = {0.0f, 0.5f};
        Color[] colors = {Color.LIGHT_GRAY, Color.DARK_GRAY};
        RadialGradientPaint p =
                new RadialGradientPaint(center, radius,
                        dist, colors,
                        MultipleGradientPaint.CycleMethod.NO_CYCLE);
        g2d.setPaint(p);
        g2d.fillOval((int) (load_x-radius/2), (int) (load_y-radius/2),150,150);
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
