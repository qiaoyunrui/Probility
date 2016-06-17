package com.Juhezi.Probility.DataStructure;

/**
 * 作为int或者double的包装类
 * <p>
 * Created by qiaoyunrui on 16-6-17.
 */
public class JuNumber {

    private static final int ALL_INT = 0;
    private static final int ALL_DOUBLE = 1;
    private static final int DIFFERENT = -1;

    public JuNumber(Object o) {

    }

    public static JuNumber add(JuNumber numberA, JuNumber numberB) {
        int judgeEnd = judgeClass(numberA, numberB);
        JuNumber result;
        switch (judgeEnd) {
            case ALL_INT:
                result = new JuInteger(
                        (int) numberA.getData() + (int) numberB.getData());
                break;
            case ALL_DOUBLE:
                result = new JuDouble(
                        (double) numberA.getData() + (double) numberB.getData());
                break;
            default:
                result = new JuNumber(0);
        }
        return result;
    }

    public static JuNumber modify(JuNumber numberA, JuNumber numberB) {
        int judgeEnd = judgeClass(numberA, numberB);
        JuNumber result;
        switch (judgeEnd) {
            case ALL_INT:
                result = new JuInteger(
                        (int) numberA.getData() - (int) numberB.getData());
                break;
            case ALL_DOUBLE:
                result = new JuDouble(
                        (double) numberA.getData() - (double) numberB.getData());
                break;
            default:
                result = new JuNumber(0);
        }

        return result;
    }

    public static JuNumber multiply(JuNumber numberA, JuNumber numberB) {
        int judgeEnd = judgeClass(numberA, numberB);
        JuNumber result;
        switch (judgeEnd) {
            case ALL_INT:
                result = new JuInteger(
                        (int) numberA.getData() * (int) numberB.getData());
                break;
            case ALL_DOUBLE:
                result = new JuDouble(
                        (double) numberA.getData() * (double) numberB.getData());
                break;
            default:
                result = new JuNumber(0);
        }

        return result;
    }

    /**
     * 判断所属类别
     *
     * @return
     */
    public static int judgeClass(JuNumber numberA, JuNumber numberB) {

        if (numberA instanceof JuInteger && numberB instanceof JuInteger) {
            return ALL_INT;
        }
        if (numberA instanceof JuDouble && numberB instanceof JuDouble) {
            return ALL_DOUBLE;
        }

        return DIFFERENT;
    }

    /**
     * 获取所包装的数据
     *
     * @return
     */
    public Object getData() {
        return 0;
    }

    /**
     * 设置所包装的数据
     *
     * @param o
     */
    public void setData(Object o) {

    }

}
