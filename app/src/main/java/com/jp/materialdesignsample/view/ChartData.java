package com.jp.materialdesignsample.view;

public class ChartData {
    private double XValue;
    private double YValue;

    public ChartData(double x, double y) {
        XValue = x;
        YValue = y;
    }

    public double getXValue() {
        return XValue;
    }

    public double getYValue() {
        return YValue;
    }

    public void setYValue(double value) {
        YValue = value;
    }
}
