package com.Juhezi.Probility.Widgets;

import com.Juhezi.Probility.DataStructure.Bundle;
import com.Juhezi.Probility.DataStructure.Probility;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-6-22.
 */
public class JuCanvas extends JPanel {

    private java.util.List<Double> list = new ArrayList<>();
    private int width;
    private int height;
    private int radius;
    private DecimalFormat df = new DecimalFormat("0.000");
    private Bundle bundle = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        width = getWidth();
        height = getHeight();

        if (list != null && list.size() != 0) {
            int marginX = width * 2 / (list.size() + 2);   //间隔
            int marginY = height / 3;
            for (int i = 0; i < list.size(); i++) {
                radius = width / (list.size() * 2);
                if (i < list.size() / 2) {   //上一层
                    g.setColor(new Color(230, 74, 25));
                    g.fillOval((i + 1) * marginX - radius, marginY - radius, radius * 2, radius * 2);
                    g.setColor(Color.BLACK);
                    g.drawString(i + "", (i + 1) * marginX - 7, marginY + 7);
                    g.drawString("P: " + df.format(list.get(i)), (i + 1) * marginX - radius / 2, marginY + 30);
                } else {    //下一层
                    g.setColor(new Color(230, 74, 25));
                    g.fillOval((i + 1 - list.size() / 2) * marginX - radius, marginY * 2 - radius, radius * 2, radius * 2);
                    g.setColor(Color.BLACK);
                    g.drawString(i + "", (i + 1 - list.size() / 2) * marginX - 7, marginY * 2 + 7);
                    g.drawString("P: " + df.format(list.get(i)), (i + 1 - list.size() / 2) * marginX - radius / 2, marginY * 2 + 30);
                }
            }
            g.drawString("村庄个数为：" + bundle.getVillageNum() + "  监狱所在村庄为：" + bundle.getPrison() + "  逃狱天数为：" + bundle.getDay(),
                    5, 20);
        } else {
            g.drawString("...", width / 2, height / 2);
        }

    }

    public void setList(List<Double> list) {
        this.list = list;
        repaint();
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
        repaint();
    }

}
