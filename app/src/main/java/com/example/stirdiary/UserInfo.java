package com.example.stirdiary;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends AppCompatActivity {

    private BarChart barChart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.personal_info_page);
        initView();
    }

    private void initView() {
        barChart1 = (BarChart) findViewById(R.id.Bar_chat1);
        showBarChartAlong();
    }

    /**
     * 显示单条柱状图
     */
    private void showBarChartAlong() {
        BarChartManager barChartManager = new BarChartManager(barChart1);
        List<BarEntry> yVals = new ArrayList<>();
        yVals.add(new BarEntry(1f, 30));
        yVals.add(new BarEntry(2f, 50));
        yVals.add(new BarEntry(3f, 60));
        yVals.add(new BarEntry(4f, 60f));
        yVals.add(new BarEntry(5f, 70f));
        yVals.add(new BarEntry(6f, 10f));
        yVals.add(new BarEntry(7f, 20f));
        yVals.add(new BarEntry(8f, 80f));
        yVals.add(new BarEntry(9f, 50f));
        yVals.add(new BarEntry(10f, 90f));
        yVals.add(new BarEntry(11f, 20f));
        yVals.add(new BarEntry(12f, 80f));
        String label = "";
        barChartManager.showBarChart(yVals, label);
    }
}