package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
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

    private WineAdapter mAdapter;

    public void onItemClick(View view) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
        Intent it = new Intent(OwnDiary_list.this, DiaryReading.class);
        it.putExtra("file", diaryList.get(childAdapterPosition));
        startActivity(it);
    }

    private void initDiary() {
        for (int i = 0; i < 10; i++) {
            Diary test1 = new Diary();
            test1.setDate("2020-6-17");
            test1.setDiary_title("软工真好玩");
            diaryList.add(test1);
            Diary test2 = new Diary();
            test2.setDate("2020-6-18");
            test2.setDiary_title("我爱软工");
            diaryList.add(test2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.own_diary_list);
        initDiary();
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new WineAdapter(diaryList, OwnDiary_list.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

}
