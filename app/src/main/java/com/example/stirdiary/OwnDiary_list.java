package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class OwnDiary_list extends AppCompatActivity {

    private List<Diary> diaryList = new ArrayList<Diary>();
    private int curMonth = 6;
    private RecyclerView mRecyclerView;
    private ViewSwitcher mVS;
    private WineAdapter mAdapter;
    private TextView month_text;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;
    private DBDiaryDao mdbdao = new DBDiaryDao(this);

    private String start_date_generate(int month) {
        if (month < 10) {
            return "2020-0" + month + "-00";
        } else {
            return "2020-" + month + "-00";
        }
    }

    private String end_date_generate(int month) {
        if (month < 10) {
            return "2020-0" + month + "31";
        } else {
            return "2020-" + month + "31";
        }
    }

    public void onItemClick(View view) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
        Intent it = new Intent(OwnDiary_list.this, DiaryReading.class);
        it.putExtra("file", diaryList.get(childAdapterPosition));
        startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.own_diary_list);
        diaryList = mdbdao.selectBetweenDate(start_date_generate(curMonth), end_date_generate(curMonth));
        month_text = findViewById(R.id.month);

        //切换事件
        ImageView switchButton = findViewById(R.id.switch_button);
        mVS = findViewById(R.id.switcher);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVS.showNext();
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new WineAdapter(diaryList, OwnDiary_list.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchDownX = event.getX();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchUpX = event.getX();
                    if (touchUpX - touchDownX > 100) {
                        curMonth -= 1;
                        if (curMonth < 10) {
                            month_text.setText("2020-0" + curMonth);
                        } else {
                            month_text.setText("2020" + curMonth);
                        }
                        diaryList = mdbdao.selectBetweenDate(start_date_generate(curMonth), end_date_generate(curMonth));
                        mAdapter = new WineAdapter(diaryList, OwnDiary_list.this);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        return true;
                    } else if (touchUpX - touchDownX < 100) {
                        curMonth += 1;
                        if (curMonth < 10) {
                            month_text.setText("2020-0" + curMonth);
                        } else {
                            month_text.setText("2020" + curMonth);
                        }
                        diaryList = mdbdao.selectBetweenDate(start_date_generate(curMonth), end_date_generate(curMonth));
                        mAdapter = new WineAdapter(diaryList, OwnDiary_list.this);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        return true;
                    }
                }


                return false;
            }
        });
    }

}
