package com.example.stirdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        SVGImageView wineImg;
        FileInputStream input = null;
        try {
            input = mContext.openFileInput(diary.getUid());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SVG svg = null;
        try {
            svg = SVG.getFromInputStream(input);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        wineImg = new SVGImageView(mContext);
        wineImg.setSVG(svg);
        myViewHolder.mLinearLO.addView(wineImg, -1);
    }

    @Override
    public int getItemCount() {
        return mDiaryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView wineTitle;
        TextView wineDate;
        LinearLayout mLinearLO;

        public MyViewHolder(View itemView) {
            super(itemView);
            mLinearLO = itemView.findViewById(R.id.linearContainer);
            wineTitle = itemView.findViewById(R.id.wine_title);
            wineDate = itemView.findViewById(R.id.wine_date);
        }
    }
}
