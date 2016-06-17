package com.Juhezi.Probility.DataStructure;

/**
 * Created by qiaoyunrui on 16-6-17.
 */
public class JuInteger extends JuNumber {

    private int data = 0;

    public JuInteger(Object data) {
        super(data);
        this.data = (int) data;
    }

    @Override
    public void setData(Object o) {
        this.data = (int) o;
    }

    @Override
    public Object getData() {
        return data;
    }

}
