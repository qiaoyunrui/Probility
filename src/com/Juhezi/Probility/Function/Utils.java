package com.Juhezi.Probility.Function;

import com.Juhezi.Probility.DataStructure.JuInteger;
import com.Juhezi.Probility.DataStructure.Matrix;
import com.Juhezi.Probility.DataStructure.Probility;

import java.util.*;

/**
 * 工具类
 * <p>
 * Created by qiaoyunrui on 16-6-17.
 */
public class Utils {

    /**
     * 从控制台输入,int类型的
     */
    public static Matrix cinMatrix(Scanner in, int row, int colume) {
        Matrix matrix = new Matrix(row, colume, Matrix.INT_FLAG);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colume; j++) {
                matrix.put(i, j, new JuInteger(in.nextInt()));
            }
        }
        return matrix;
    }

    /**
     * 获取汉尼拔概率矩阵
     *
     * @param day
     * @return
     */
    public static Matrix getHannibaMatrix(Matrix proMatrix, Matrix singleMatrix, int day) {
        for (int i = 1; i < day; i++) {
            singleMatrix = proMatrix.multiply(singleMatrix);
        }
        return singleMatrix;
    }

    /**
     * 求概率线性表
     *
     * @param proMatrix
     * @param priList
     * @return
     */
    public static List<Probility> getProbilityList(Matrix proMatrix, int source, List<Probility> priList) {
        List<Probility> result = new ArrayList<>();
        if (priList == null) {   //第一次
            for (int i = 0; i < proMatrix.getRow(); i++) {
                result.add(new Probility(source, i, (double) proMatrix.get(i, source).getData()));
            }
            return result;
        }
        Probility temp;
        for (int i = 0; i < priList.size(); i++) { //对线性表进行循环
            if (priList.get(i).getProbility() == 0.0) {
                continue;
            }
            for (int j = 0; j < proMatrix.getRow(); j++) {
                int middle = priList.get(i).getDestination();   //中间站
                if ((double) proMatrix.get(middle, j).getData() != 0.0) {
                    //j - 目的地
                    //priList.get(i).getProbility() 源站到中间站的概率
                    //(double)proMatrix.get(middle,j).getData()     中间站到目的站的概率
                    temp = new Probility(source, j, priList.get(i).getProbility() *
                            (double) proMatrix.get(j, middle).getData());
                    /*System.out.println(priList.get(i).getProbility() +
                            " * " +
                            (double)proMatrix.get(j,middle).getData() +
                            " = " +
                            temp.getProbility());*/
                    result.add(temp);
                }
            }
        }
        priList.clear();
        priList = null;
        return result;
    }


    public static List<Probility> getProbilityAfterDays(Matrix proMatrixm, int source, int day) {
        List<Probility> list = null;
        for (int i = 1; i <= day; i++) {
            list = getProbilityList(proMatrixm, source, list);
        }
        return list;

    }

    /**
     * 动态规划处理
     * @return
     */
    public static Matrix handleByMatrix() {
        return null;
    }

    /**
     * 蛮力法处理
     * @return
     */
    public static List<Probility> handleByList() {
        return null;
    }

}
