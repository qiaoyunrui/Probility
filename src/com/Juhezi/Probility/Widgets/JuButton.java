package com.Juhezi.Probility.Widgets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by qiaoyunrui on 16-6-22.
 */
public class JuButton extends JButton {

    private static final long serialVersionUID = 39082560987930759L;
    private Color colorEnter = new Color(205, 255, 205);
    private Color colorQuit = new Color(51, 154, 47);
    public static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
    private boolean hover;

    public JuButton(Color colorEnter, Color colorQuit) {
        this.colorEnter = colorEnter;
        this.colorQuit = colorQuit;
        setBorderPainted(false);    //不绘制边框
        setForeground(colorQuit);
        setFocusPainted(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(BUTTON_FOREGROUND_COLOR);
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(colorQuit);
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int height = getHeight();
        int width = getWidth();
        float tran = 1f;
        if (!hover) {
            tran = 0.5f;
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p1;
        GradientPaint p2;
        if (getModel().isPressed()) {
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0),
                    0, height - 1, new Color(100, 100, 100));
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50),
                    0, height - 3, new Color(255, 255, 255, 100));
        } else {
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100),
                    0, height - 1, new Color(0, 0, 0));
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100),
                    0, height - 3, new Color(0, 0, 0, 50));
        }
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, tran));
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(
                0, 0, width - 1, height - 1, 20, 20);
        Shape clip = g2d.getClip();
        g2d.clip(r2d);
        GradientPaint gp = new GradientPaint(0f, 0f, colorEnter, 0f,
                height, colorQuit, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        g2d.setClip(clip);
        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, width - 1, height - 1, 20, 20);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, width - 3, height - 3, 18, 18);
        g2d.dispose();
        super.paintComponent(g);
    }

    public void setColorQuit(Color colorQuit) {
        this.colorQuit = colorQuit;
    }

    public void setColorEnter(Color colorEnter) {
        this.colorEnter = colorEnter;
    }
}


