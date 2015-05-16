package com.jp.materialdesignsample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuu.phung on 15/04/2015.
 */
public class ChartView extends View {
    private static final int LINE_WIDTH = 1;
    private static final int DATA_LINE_WIDTH = 3;
    private static final int ARROW_HEAD_OFFSET = 5;
    private static final float TEXT_SIZE = 15f;

    private List<ChartData> dataList;
    private double xMaxValue, yMaxValue;
    private int xMaxChartValue, yMaxChartValue;
    private int yStepValue, xStepValue;
    private int yStepPixel, xStepPixel;

    public ChartView(Context context) {
        super(context);
        initData();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawChart(canvas);
    }

    private void initData() {
        dataList = new ArrayList<>();

        xStepValue = 1;
        xStepPixel = 1;

        yStepValue = 1;
        yStepPixel = 1;
    }

    public void setDataList(List<ChartData> data) {
        dataList = data;
        prepareData(data);

        invalidate();
    }

    private void drawChart(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setPathEffect(new CornerPathEffect(2));
        paint.setAntiAlias(true);

        int zeroPointX = 50 - LINE_WIDTH;
        int zeroPointY = canvas.getHeight() - 50 + LINE_WIDTH;
        Point zeroPoint = new Point(zeroPointX, zeroPointY);

        int xMax = canvas.getWidth() - 20;
        Point maxOxPoint = new Point(xMax, zeroPoint.y);

        int yMax = 20;
        Point maxOyPoint = new Point(zeroPoint.x, yMax);

        setupLines(canvas, paint, zeroPoint, maxOxPoint, maxOyPoint);

        if (dataList != null && dataList.size() > 0) {

            xStepPixel = (xMax - zeroPointX) / (xMaxChartValue / xStepValue);
            yStepPixel = (zeroPointY - yMax) / (yMaxChartValue / yStepValue);

            setupLabels(canvas, paint, zeroPoint, maxOxPoint, maxOyPoint);

            drawDataLine(canvas, paint, zeroPoint);
        }

        Log.d("CHART", String.format("zeroPointX=%s zeroPointY=%s xMax=%s yMax=%s xStepPixel=%s yStepPixel=%s",
                String.valueOf(zeroPointX), String.valueOf(zeroPointY), String.valueOf(xMax), String.valueOf(yMax), String.valueOf(xStepPixel), String.valueOf(yStepPixel)));
    }

    private void prepareData(List<ChartData> data) {
        xMaxValue = getXMaxData(data);
        yMaxValue = getYMaxData(data);

        xStepValue = getStepValue(xMaxValue);
        yStepValue = getStepValue(yMaxValue);

        xMaxChartValue = (int) (Math.ceil(xMaxValue / xStepValue) * xStepValue);
        yMaxChartValue = (int) (Math.ceil(yMaxValue / yStepValue) * yStepValue);

        Log.d("CHART", String.format("xMaxValue=%s yMaxValue=%s xStepValue=%s yStepValue=%s xMaxChartValue=%s yMaxChartValue=%s",
                String.valueOf(xMaxValue), String.valueOf(yMaxValue), String.valueOf(xStepValue), String.valueOf(yStepValue), String.valueOf(xMaxChartValue), String.valueOf(yMaxChartValue)));
    }

    private int getStepValue(double maxValue) {
        if (maxValue <= 6) {
            return 1;
        } else if (maxValue <= 12) {
            return 2;
        } else if (maxValue <= 30) {
            return 5;
        } else if (maxValue <= 60) {
            return 10;
        } else if (maxValue <= 90) {
            return 15;
        } else if (maxValue <= 120) {
            return 20;
        }
        return 50;
    }

    private void setupLines(Canvas canvas, Paint paint, Point zeroPoint, Point maxOxPoint, Point maxOyPoint) {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);

        // Ox
        canvas.drawLine(zeroPoint.x, zeroPoint.y, maxOxPoint.x, maxOxPoint.y, paint);
        canvas.drawLine(maxOxPoint.x, maxOxPoint.y, maxOxPoint.x - ARROW_HEAD_OFFSET, maxOxPoint.y + ARROW_HEAD_OFFSET, paint);
        canvas.drawLine(maxOxPoint.x, maxOxPoint.y, maxOxPoint.x - ARROW_HEAD_OFFSET, maxOxPoint.y - ARROW_HEAD_OFFSET, paint);
        // Oy
        int expandLength = 10;
        canvas.drawLine(zeroPoint.x, zeroPoint.y, maxOyPoint.x, maxOyPoint.y - expandLength, paint);
        canvas.drawLine(maxOyPoint.x, maxOyPoint.y - expandLength, maxOyPoint.x + ARROW_HEAD_OFFSET, maxOyPoint.y + ARROW_HEAD_OFFSET - expandLength, paint);
        canvas.drawLine(maxOyPoint.x, maxOyPoint.y - expandLength, maxOyPoint.x - ARROW_HEAD_OFFSET, maxOyPoint.y + ARROW_HEAD_OFFSET - expandLength, paint);
    }

    private void setupLabels(Canvas canvas, Paint paint, Point zeroPoint, Point maxOxPoint, Point maxOyPoint) {
        paint.setColor(Color.GREEN);
        paint.setTextSize(TEXT_SIZE);

        Rect textBounds = new Rect();
        String text;
        int xTextPosition, yTextPosition;

        // Labels on Ox
        Point horizontalPoint = new Point(zeroPoint.x + xStepPixel + LINE_WIDTH, zeroPoint.y + 20);
        int XValue = xStepValue;
        while (horizontalPoint.x < maxOxPoint.x) {
            text = String.valueOf(XValue);
            paint.getTextBounds(text, 0, text.length(), textBounds);

            xTextPosition = horizontalPoint.x - (textBounds.width() / 2);
            yTextPosition = horizontalPoint.y;
            canvas.drawText(String.valueOf(XValue), xTextPosition, yTextPosition, paint);

            horizontalPoint.set(horizontalPoint.x + xStepPixel, horizontalPoint.y);
            XValue += xStepValue;
        }

        // Labels on Oy
        Point verticalPoint = new Point(zeroPoint.x - 20, zeroPoint.y - yStepPixel - LINE_WIDTH);
        int YValue = yStepValue;

        while (verticalPoint.y >= maxOyPoint.y) {
            text = String.valueOf(YValue);
            paint.getTextBounds(text, 0, text.length(), textBounds);

            xTextPosition = verticalPoint.x - (textBounds.width() / 2);
            yTextPosition = verticalPoint.y + (textBounds.height() / 2);
            canvas.drawText(String.valueOf(YValue), xTextPosition, yTextPosition, paint);

            verticalPoint.set(verticalPoint.x, verticalPoint.y - yStepPixel);
            YValue += yStepValue;
        }

        // Vertical lines
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
        Point p = new Point(zeroPoint.x + xStepPixel + LINE_WIDTH, zeroPoint.y);
        while (p.x < maxOxPoint.x) {
            canvas.drawLine(p.x, p.y - LINE_WIDTH, p.x, maxOyPoint.y, paint);
            p.set(p.x + xStepPixel, p.y);
        }
    }

    private void drawDataLine(Canvas canvas, Paint paint, Point zeroPoint) {
        paint.setStrokeWidth(DATA_LINE_WIDTH);

        for (int i = 0; i < dataList.size() - 1; i++) {
            PointF p1 = getPointByData(dataList.get(i), zeroPoint);
            PointF p2 = getPointByData(dataList.get(i + 1), zeroPoint);

            int c1 = getColorByY(dataList.get(i).getYValue());
            int c2 = getColorByY(dataList.get(i + 1).getYValue());

            Shader tGradientShader = new LinearGradient(p1.x, p1.y, p2.x, p2.y, c1, c2, Shader.TileMode.MIRROR);
            paint.setShader(tGradientShader);

            canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
        }
    }

    private PointF getPointByData(ChartData data, Point zeroPoint) {
        double x = zeroPoint.x + getXByValue(data.getXValue()) + LINE_WIDTH;
        double y = zeroPoint.y - getYByValue(data.getYValue()) - LINE_WIDTH;

        return new PointF((float) x, (float) y);
    }

    private double getXByValue(double value) {
        double x1 = Math.floor(value / xStepValue) * xStepPixel;
        double x2 = ((value % xStepValue) * xStepPixel) / xStepValue;

        return x1 + x2;
    }

    private double getYByValue(double value) {
        double y1 = Math.floor(value / yStepValue) * yStepPixel;
        double y2 = ((value % yStepValue) * yStepPixel) / yStepValue;

        return y1 + y2;
    }

    private int getColorByY(double yValue) {
        float tColorRangeValue = 120f;

        float H = (float) (tColorRangeValue - ((yValue / yMaxValue) * tColorRangeValue));
        float S = 0.9f;
        float V = 0.9f;

        if (yMaxValue == 0) {
            H = tColorRangeValue;
        }

        return Color.HSVToColor(new float[]{H, S, V});
    }

    private double getXMaxData(List<ChartData> dataList) {
        double max = 0;
        for (ChartData data : dataList) {
            if (data.getXValue() > max) {
                max = data.getXValue();
            }
        }

        return max;
    }

    private double getYMaxData(List<ChartData> dataList) {
        double max = 0;
        for (ChartData data : dataList) {
            if (data.getYValue() > max) {
                max = data.getYValue();
            }
        }

        return max;
    }
}

