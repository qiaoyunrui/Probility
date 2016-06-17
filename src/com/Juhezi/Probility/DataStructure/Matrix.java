package com.Juhezi.Probility.DataStructure;

/**
 * 矩阵类
 * <p>
 * Created by qiaoyunrui on 16-6-17.
 */
public class Matrix {

    public static final int INT_FLAG = 0;
    public static final int DOUBLE_FLAG = 1;
    public static final int NONE = 2;

    private int flag;
    private int row;    //行 x
    private int column;     //列 y
    private JuNumber[][] datas;  //用数组放置矩阵元素

    public Matrix(int row, int column, int FLAG) {
        this.row = row;
        this.column = column;
        this.flag = FLAG;
        datas = new JuNumber[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (flag == INT_FLAG) {
                    datas[i][j] = new JuInteger(0);
                } else if (flag == DOUBLE_FLAG) {
                    datas[i][j] = new JuDouble(0.0);
                } else {
                    datas[i][j] = new JuNumber(0);
                }

            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void put(int row, int column, JuNumber data) {
        datas[row][column] = data;
    }

    public JuNumber get(int row, int colume) {
        return datas[row][colume];
    }

    /**
     * 矩阵乘法
     *
     * @param matrix
     * @return
     */
    public Matrix multiply(Matrix matrix) {
        if (column != matrix.getRow() || flag != matrix.getFlag()) { //两个矩阵无法相乘
            return new Matrix(0, 0, NONE);
        }
        Matrix resultMatrix = new Matrix(row, matrix.getColumn(), flag);
        JuNumber result;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                if (flag == INT_FLAG) {
                    result = new JuInteger(0);
                } else if (flag == DOUBLE_FLAG) {
                    result = new JuDouble(0.0);
                } else {
                    result = new JuNumber(0);
                }
                for (int k = 0; k < column; k++) {
                    result = JuNumber.add(result, JuNumber.multiply(datas[i][k], matrix.get(k, j)));
                }
                resultMatrix.put(i, j, result);
            }
        }
        return resultMatrix;
    }

    public void show() {

        System.out.println("-----");

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                System.out.print(datas[x][y].getData() + " ");
            }
            System.out.println();
        }
    }

    public int getFlag() {
        return flag;
    }

    /**
     * 获取单列矩阵
     *
     * @return
     */
    public Matrix getSignleColumeMatrix(int column) {
        if (column >= this.column) {    //不存在此列
            return new Matrix(0, 0, INT_FLAG);
        }
        Matrix singleMatrix = new Matrix(row, 1, getFlag());
        for (int i = 0; i < row; i++) {
            singleMatrix.put(i, 0, get(i, column));
        }
        return singleMatrix;
    }

    /**
     * 将矩阵转换为概率矩阵
     *
     * @return
     */
    public Matrix turn2Probility() {
        Matrix result;
        if (this.getFlag() != Matrix.INT_FLAG) {
            result = new Matrix(0, 0, Matrix.INT_FLAG);
        }
        result = new Matrix(this.getRow(), this.getColumn(), Matrix.DOUBLE_FLAG);
        int total = 1;
        for (int i = 0; i < column; i++) {
            total = 0;
            for (int j = 0; j < row; j++) {
                if ((int) this.get(j, i).getData() > 0) {  //存在通路
                    total++;
                }
            }
            double probility;
            if (total <= 0) {
                probility = 0.0;
            } else {
                probility = 1.0 / total;
            }
            for (int j = 0; j < row; j++) {
                if ((int) this.get(j, i).getData() > 0) {  //存在通路
                    result.put(j, i, new JuDouble(probility));
                } else {
                    result.put(j, i, new JuDouble(0.0));
                }
            }

        }
        return result;
    }
}

