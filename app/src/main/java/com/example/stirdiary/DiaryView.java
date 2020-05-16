package com.example.stirdiary;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

public class DiaryView extends View {
    private String imgSrc;
    private String contentSrc;

    public DiaryView(Context context) {
        this(context, null);
    }

    public DiaryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiaryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DiaryView);
        imgSrc = ta.getString(R.styleable.DiaryView_imgsrc);
        contentSrc = ta.getString((R.styleable.DiaryView_contentsrc));
        ta.recycle();
    }

}
