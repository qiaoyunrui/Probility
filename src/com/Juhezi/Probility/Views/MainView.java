package com.Juhezi.Probility.Views;

import com.Juhezi.Probility.DataStructure.Bundle;
import com.Juhezi.Probility.DataStructure.Matrix;
import com.Juhezi.Probility.Function.Utils;
import com.Juhezi.Probility.Widgets.JuButton;
import com.Juhezi.Probility.Widgets.JuCanvas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicIconFactory;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 主界面
 * <p>
 * Created by qiaoyunrui on 16-6-21.
 */
public class MainView extends JFrame {

    private static final String DATA_PATH = "/home/qiaoyunrui/probility_data/data.txt";
    private static final String RED_ICON_PATH = "./res/2-0btn_13.png";
    private static final String TITLE = "寻找逃狱的汉尼拔博士";
    private static final String ICON_PATH = "./res/icon_old_man.jpg";

    private java.util.List<Double> list = new ArrayList<>();
    private Font controlFont;
    private Font canvasFont;
    private Font consoleFont;
    private JuButton runBtn;
    private JuButton chooseBtn;  //选择文件按钮
    private JLabel dataPathJL;
    private JTextField dataPathTF;  //显示地址
    private JFileChooser mJFileChooser;
    private JPanel controlPanel; //控制面板
    private JuCanvas canvasPanel; //绘图面板
    private JPanel consolePanel;    //数据输出面板
    private JPanel nonePanel;
    private BoxLayout mBoxLayout;
    private BorderLayout mBorderLayout;
    private JuCanvas canvas;
    private JScrollPane scroll;
    private ImageIcon imageIcon = new ImageIcon(ICON_PATH);

    private JTextArea mJTextArea;   //输出面板
    private Icon iconRed;   //红色图标

    private Border mBorder; //边框

    private String path;

    public MainView() {

        initData();

        initView();

        initControlPanel();

        initCanvasPanel();

        initConsolePanel();

        initMain();

        initEvent();
    }

    private void initData() {
        path = DATA_PATH;
        controlFont = new Font("Arial", Font.BOLD, 15);
        canvasFont = new Font("System", Font.BOLD, 20);
        consoleFont = new Font("Arial", Font.PLAIN, 20);
        mBorder = BorderFactory.createLineBorder(Color.darkGray, 3);
        iconRed = new ImageIcon(RED_ICON_PATH);
    }

    private void initView() {
        runBtn = new JuButton(new Color(255, 87, 34), new Color(230, 74, 25));
        mJFileChooser = new JFileChooser(DATA_PATH);
        chooseBtn = new JuButton(new Color(233, 22, 99), new Color(194, 24, 88));
        dataPathTF = new JTextField(DATA_PATH);
        mJTextArea = new JTextArea("Hello");
        scroll = new JScrollPane(mJTextArea);
        dataPathJL = new JLabel("Path：");
        controlPanel = new JPanel();
        canvasPanel = new JuCanvas();
        consolePanel = new JPanel();
        nonePanel = new JPanel();
        mBoxLayout = new BoxLayout(controlPanel, BoxLayout.X_AXIS);
        mBorderLayout = new BorderLayout();
        canvas = new JuCanvas();
    }

    private void initControlPanel() {
        controlPanel.setSize(800, 100);
        controlPanel.setLayout(mBoxLayout);

        dataPathJL.setFont(controlFont);
        controlPanel.add(dataPathJL);

        dataPathTF.setFont(controlFont);
        controlPanel.add(dataPathTF);

        chooseBtn.setFont(controlFont);
        controlPanel.add(chooseBtn);

        runBtn.setText("Run");
        runBtn.setFont(controlFont);

        chooseBtn.setText("Choose");
        chooseBtn.setFont(controlFont);
        controlPanel.add(runBtn);

        controlPanel.setBorder(mBorder);
    }

    private void initCanvasPanel() {
        canvasPanel.setBackground(new Color(97, 194, 238));
        canvasPanel.setBorder(mBorder);
        canvasPanel.setFont(canvasFont);
    }

    private void initConsolePanel() {
        consolePanel.setSize(800, 100);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mJTextArea.setLineWrap(true);
        mJTextArea.setAutoscrolls(true);
        mJTextArea.setEditable(false);
        mJTextArea.setFont(consoleFont);
        mJTextArea.setSize(consolePanel.getWidth(), consolePanel.getHeight());
        mJTextArea.setPreferredSize(new Dimension(mJTextArea.getWidth(), mJTextArea.getHeight()));
        mJTextArea.setBackground(Color.lightGray);
        consolePanel.add(scroll);
        consolePanel.setBorder(mBorder);
    }

    private void initMain() {
        setLayout(mBorderLayout);
        add(controlPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
        add(consolePanel, BorderLayout.SOUTH);
        setBounds(100, 100, 900, 900);
        setVisible(true);
        setTitle(TITLE);
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initEvent() {
        chooseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mJFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mJFileChooser.showDialog(new JLabel(), "Choose");
                File file = mJFileChooser.getSelectedFile();
                if (file != null) {
                    path = mJFileChooser.getSelectedFile().getAbsolutePath();
                    dataPathTF.setText(path);
                }
            }
        });

        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        DecimalFormat df = new DecimalFormat("0.00000000");
                        Bundle bundle = new Bundle(path); //从文件中获取部分输入
                        Matrix matrix = bundle.getMatrix(); //获取输入的矩阵
                        int day = bundle.getDay();
                        int prison = bundle.getPrison();
                        int villageNum = bundle.getVillageNum();
                        matrix = matrix.turn2Probility();
                        Matrix singleMatrix = matrix.getSignleColumeMatrix(prison);
                        singleMatrix = Utils.getHannibaMatrix(matrix, singleMatrix, day);
                        StringBuffer sf = new StringBuffer();
                        list.clear();
                        for (int v = 0; v < villageNum; v++) {
                            list.add((double) singleMatrix.get(v, 0).getData());
                            sf.append(df.format(singleMatrix.get(v, 0).getData()) + " ");    //输出概率
                        }
                        sf.append("\n");
                        mJTextArea.setText(sf.toString());
                        canvasPanel.setBundle(bundle);
                        canvasPanel.setList(list);
                    }
                }.start();
            }
        });
    }


}
