package com.Juhezi.Probility.Function;

import com.Juhezi.Probility.DataStructure.JuInteger;
import com.Juhezi.Probility.DataStructure.Matrix;

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

}
