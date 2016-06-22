package com.Juhezi.Probility.DataStructure;

import com.Juhezi.Probility.Function.Utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * 包装所有输入数据的包裹
 * Created by qiaoyunrui on 16-6-22.
 */
public class Bundle {

    private int villageNum; //村庄个数
    private int day;    //逃狱的天数
    private int prison; //监狱所在的村庄

    private Matrix matrix;  //输入矩阵

    private Scanner in = null;

    /**
     * 从文件中输入
     *
     * @param path
     */
    public Bundle(String path) {
        File file = new File(path);
        try {
            in = new Scanner(file);
            villageNum = in.nextInt();
            day = in.nextInt();
            prison = in.nextInt();
            matrix = Utils.cinMatrix(in, villageNum, villageNum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    public int getVillageNum() {
        return villageNum;
    }

    public int getDay() {
        return day;
    }

    public int getPrison() {
        return prison;
    }

    public Matrix getMatrix() {
        return matrix;
    }

}
