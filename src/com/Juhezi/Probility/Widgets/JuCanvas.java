package com.Juhezi.Probility.Widgets;

import com.Juhezi.Probility.DataStructure.Bundle;
import com.Juhezi.Probility.DataStructure.Matrix;
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
    private java.util.List<Node> nodeList = new ArrayList<>();
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
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        if (list != null && list.size() != 0) {
            int marginX = width * 2 / (list.size() + 2);   //间隔
            int marginY = height / 3;

            for (int i = 0; i < list.size(); i++){
                if (i < list.size() / 2) {   //上一层
                    nodeList.add(i, new Node((i + 1) * marginX, marginY));
                } else {    //下一层
                    nodeList.add(i, new Node((i + 1 - list.size() / 2) * marginX, marginY * 2));
                }
            }
            drawLine(g2d, bundle.getMatrix(), nodeList);
            for (int i = 0; i < list.size(); i++) {
                radius = width / (list.size() * 2);
                if (i == bundle.getPrison()) {
                    g2d.setColor(new Color(96, 125, 139));
                } else {
                    g2d.setColor(new Color(230, 74, 25));
                }
                String str = "P: " + df.format(list.get(i));
                if (i < list.size() / 2) {   //上一层
//                    nodeList.add(i, new Node((i + 1) * marginX, marginY));
//                    System.out.println((i + 1) * marginX + " " + marginY);
                    g2d.fillOval((i + 1) * marginX - radius, marginY - radius, radius * 2, radius * 2);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(i + "", (i + 1) * marginX - width / 100, marginY + height / 100);
                    g2d.drawString(str, (i + 1) * marginX - str.length() * radius / 13, marginY + height / 20);
                } else {    //下一层
//                    nodeList.add(i, new Node((i + 1 - list.size() / 2) * marginX, marginY * 2));
                    g2d.fillOval((i + 1 - list.size() / 2) * marginX - radius, marginY * 2 - radius, radius * 2, radius * 2);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(i + "", (i + 1 - list.size() / 2) * marginX - width / 100, marginY * 2 + height / 100);
                    g2d.drawString(str, (i + 1 - list.size() / 2) * marginX - str.length() * radius / 13, marginY * 2 + 30);
                }
            }


            g2d.setColor(Color.BLACK);
            g2d.drawString("村庄个数为：" + bundle.getVillageNum() + "  监狱所在村庄为：" + bundle.getPrison() + "  逃狱天数为：" + bundle.getDay(),
                    5, 20);
        } else {
            String str = "Where is the doctor?";
            g2d.drawString(str, width / 2 - str.length() * 5, height / 2);
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

    /**
     * 绘制村庄之间的连线
     */
    public void drawLine(Graphics2D g2d, Matrix matrix, List<Node> list) {

        g2d.setColor(new Color(230, 74, 25));

        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j <= i; j++) {
                if ((int) matrix.get(i, j).getData() != 0) {    //此处不为0
                    g2d.drawLine(list.get(i).x, list.get(i).y, list.get(j).x, list.get(j).y);
                }
            }
        }

    }

    /**
     * 包装横坐标，纵坐标的类
     */
    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
