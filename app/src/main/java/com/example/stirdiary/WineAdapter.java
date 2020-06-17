package com.example.stirdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WineAdapter extends RecyclerView.Adapter<WineAdapter.MyViewHolder> {
    private List<Diary> mDiaryList;
    Context mContext;


    public WineAdapter(List<Diary> dilist, Context context) {
        mContext = context;
        mDiaryList = dilist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.wine_list_view, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Diary diary = mDiaryList.get(i);
        myViewHolder.wineTitle.setText(diary.getDiary_title());
        myViewHolder.wineDate.setText(diary.getDate());
        myViewHolder.wineImg.setImageResource(R.drawable.diaryicon);
    }

    @Override
    public int getItemCount() {
        return mDiaryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView wineImg;
        TextView wineTitle;
        TextView wineDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            wineImg = itemView.findViewById(R.id.wine_img);
            wineTitle = itemView.findViewById(R.id.wine_title);
            wineDate = itemView.findViewById(R.id.wine_date);
        }
    }
}
