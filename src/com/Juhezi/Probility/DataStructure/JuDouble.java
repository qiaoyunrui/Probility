package com.Juhezi.Probility.DataStructure;

/**
 * Created by qiaoyunrui on 16-6-17.
 */
public class JuDouble extends JuNumber {

    private double data = 0.0;

    public JuDouble(Object data) {
        super(data);
        this.data = (double) data;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object o) {
        this.data = (double) o;
    }

}
