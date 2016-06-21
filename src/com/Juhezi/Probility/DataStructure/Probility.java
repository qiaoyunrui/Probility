package com.Juhezi.Probility.DataStructure;

/**
 * Created by qiaoyunrui on 16-6-20.
 */
public class Probility {

    private int source;     //出发地
    private int destination;    //目的地
    private double probility;  //概率

    public Probility(int source, int destination, double probility) {
        this.source = source;
        this.destination = destination;
        this.probility = probility;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public double getProbility() {
        return probility;
    }

    public void setProbility(double probility) {
        this.probility = probility;
    }

    @Override
    public String toString() {
        return "Probility{" +
                "source=" + source +
                ", destination=" + destination +
                ", probility=" + probility +
                '}' + "\n";
    }
}
