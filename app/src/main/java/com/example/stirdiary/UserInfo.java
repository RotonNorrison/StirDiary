package com.example.stirdiary;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends AppCompatActivity {

    private BarChart barChart1;
    private DBDiaryDao mdbdao;
    private TextView sen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.personal_info_page);
        initView();
        mdbdao = new DBDiaryDao(this);

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
        int res = 0;
        int count = 0;
        List<BarEntry> yVals = new ArrayList<>();
        sen = findViewById(R.id.senitive);
        List<Diary> diaryList = new ArrayList<>();
        int[] monthcnt;
        monthcnt = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        diaryList = mdbdao.selectBetweenDate("2020-01-01", "2020-06-30");
        for (int i = 0; i < diaryList.size(); i++) {
            String s = diaryList.get(i).getDate();
            res += diaryList.get(i).getSentiment();
            count++;
            monthcnt[(s.charAt(5) - '0') * 10 + (s.charAt(6) - '0') - 1]++;
        }
        res = res * 50 / count;
        sen.setText(res + "%");
        yVals.add(new BarEntry(1f, monthcnt[0]));
        yVals.add(new BarEntry(2f, monthcnt[1]));
        yVals.add(new BarEntry(3f, monthcnt[2]));
        yVals.add(new BarEntry(4f, monthcnt[3]));
        yVals.add(new BarEntry(5f, monthcnt[4]));
        yVals.add(new BarEntry(6f, monthcnt[5]));
        yVals.add(new BarEntry(7f, monthcnt[6]));
        yVals.add(new BarEntry(8f, monthcnt[7]));
        yVals.add(new BarEntry(9f, monthcnt[8]));
        yVals.add(new BarEntry(10f, monthcnt[9]));
        yVals.add(new BarEntry(11f, monthcnt[10]));
        yVals.add(new BarEntry(12f, monthcnt[11]));
        String label = "";
        barChartManager.showBarChart(yVals, label);
    }
}