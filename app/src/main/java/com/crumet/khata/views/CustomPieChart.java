package com.crumet.khata.views;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.PieChart;

public class CustomPieChart extends PieChart {

    public CustomPieChart(Context context) {
        super(context);
    }

    public CustomPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isDrawSlicesUnderHoleEnabled() {
        return false;
    }
}
