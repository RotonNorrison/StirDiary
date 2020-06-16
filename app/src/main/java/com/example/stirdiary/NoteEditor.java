package com.example.stirdiary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class NoteEditor extends EditText {
    public int drawline = 10;
    public int lineDis = 20;
    private Rect mRect;
    private Paint mPaint;

    public NoteEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }

    public void setNotesMinLines(int lines) {
        this.drawline = lines;
        setMinLines(lines);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Rect r = mRect;
        Paint paint = mPaint;
        int basicline = 0;
        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, r);
            basicline = baseline;
            canvas.drawLine(r.left, baseline + lineDis, r.right, baseline + lineDis, paint);
        }
        if (count < drawline) {
            for (int j = 1; j < drawline; j++) {
                int baseline = basicline + j * getLineHeight();
                canvas.drawLine(r.left, baseline + lineDis, r.right, baseline + lineDis, paint);
            }
        }
        super.onDraw(canvas);
    }

}
