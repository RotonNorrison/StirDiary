package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

public class OwnDiary_list extends AppCompatActivity {
    private List<Diary> diaryList = new ArrayList<Diary>();
    private RecyclerView mRecyclerView;
    private ViewSwitcher mVS;
    private WineAdapter mAdapter;

    private DBDiaryDao mdbdao = new DBDiaryDao(this);
    ;

    public void onItemClick(View view) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
        Intent it = new Intent(OwnDiary_list.this, DiaryReading.class);
        it.putExtra("file", diaryList.get(childAdapterPosition));
        startActivity(it);
    }

    private void getDiaryByDate(String sdate, String edate) {
        diaryList = mdbdao.selectBetweenDate(sdate, edate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.own_diary_list);
        getDiaryByDate("2020-01-01", "2020-06-17");
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
    }

}
