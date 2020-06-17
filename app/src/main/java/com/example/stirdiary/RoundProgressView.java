package com.example.stirdiary;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.example.stirdiary.R;

public class RoundProgressView extends View {
    /**
     * 监视是否完成
     */
    public interface OnAchievedListener {
        void achieved();
    }

    private OnAchievedListener mListener;

    public void setOnAchievedListener(OnAchievedListener listener) {
        mListener = listener;
    }

    /**
     * context
     */
    private Context mContext;
    /**
     * 搅拌时间
     */
    private long time = 0;
    private long currentMs = 0;
    private boolean notfinished = true;
    /**
     * 整个view的宽度
     */
    private int mViewWidth;
    /**
     * 整个view的高度
     */
    private int mViewHeight;
    /**
     * 整个view的中心X坐标
     */
    private float mCenterX;
    /**
     * 整个view的中心y坐标
     */
    private float mCenterY;
    /**
     * 圆环距离view的间距
     */
    private float mOuterMargin;

    /**
     * 外层弧线画布
     */
    private RectF mDashRect = new RectF();
    /**
     * 外层弧线的画笔
     */
    private Paint mDashPaint;

    /**
     * 外层圆的画笔
     */
    private Paint mOuterPaint;
    /**
     * 外层圆半径
     */
    private float mOuterRadius;

    /**
     * 内层圆的画笔
     */
    private Paint mInnerPaint;
    /**
     * 内层圆半径
     */
    private float mInnerRadius;

    /**
     * 中心点画布
     */
    private RectF mCenterRect = new RectF();
    /**
     * 中心点的画笔
     */
    private Paint mCenterPaint;
    /**
     * 中心点背景图
     */
    private Bitmap mCenterBitmap;

    /**
     * 进度标记icon的半径
     */
    private static final int PROGRESS_RADIUS = 15;
    /**
     * 进度标记画布
     */
    private RectF mProgressRect = new RectF();
    /**
     * 进度标记画笔
     */
    private Paint mProgressPaint;
    /**
     * 进度标记
     */
    private Bitmap mProgressBitmap;
    /**
     * 进度标记的半径
     */
    private int mProgressRadius;
    /**
     * 进度条最大值
     */
    private static final int MAX_PROGRESS = 100;
    /**
     * 当前的百分值
     */
    private int mProgress;
    /**
     * 进度条标志移动后的角度, 0 ~ 360
     */
    private int mAngle = 0;
    /**
     * 进度标记开始位置的x坐标
     */
    private float mProgressPointX;
    /**
     * 进度标记开始位置的y坐标
     */
    private float mProgressPointY;

    /**
     * 进度标记到圆心的距离
     */
    private float mDistance;

    /**
     * 进度条变化监听
     */
    private OnProgressChangeListener mOnProgressChangeListener;

    /**
     * 是否手指按下的标志位
     */
    private boolean IS_PRESSED = false;

    public RoundProgressView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public RoundProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public RoundProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init() {
        mDashPaint = new Paint();
        mDashPaint.setColor(Color.parseColor("#ff7690"));
        mDashPaint.setAntiAlias(true);
        mDashPaint.setStrokeWidth(15f);
        mDashPaint.setStyle(Paint.Style.STROKE);

        mOuterPaint = new Paint();
        mOuterPaint.setColor(Color.parseColor("#201617"));
        mOuterPaint.setAntiAlias(true);

        mInnerPaint = new Paint();
        mInnerPaint.setColor(Color.parseColor("#402328"));
        mInnerPaint.setAntiAlias(true);

        //进度标记
        mProgressBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.stick);
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressRadius = dp2px(mContext, PROGRESS_RADIUS);

        //中心图标
        mCenterBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.diaryicon);
        mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //整个view的高度
        mViewWidth = getMeasuredWidth();
        //整个view的宽度
        mViewHeight = getMeasuredHeight();

        //x轴中心点
        mCenterX = mViewWidth / 2;
        //y轴中心点
        mCenterY = mViewHeight / 2;

        //整个view的大小
        int viewSize = ((mViewWidth > mViewHeight) ? mViewHeight : mViewWidth) / 2;

        //外圈半径
        mOuterRadius = viewSize * 70 / 100;

        //外圈距离view的间距
        mOuterMargin = viewSize - mOuterRadius;

        //外圈虚线
        float dashLeft = mCenterX - mOuterRadius;
        float dashTop = mCenterY - mOuterRadius;
        float dashRight = mCenterX + mOuterRadius;
        float dashBottom = mCenterY + mOuterRadius;
        mDashRect.set(dashLeft, dashTop, dashRight, dashBottom);

        //内层圈半径
        mInnerRadius = mOuterRadius * 30 / 100;
        //中心点图标
        float centerLeft = mCenterX - (mInnerRadius / 2);
        float centerTop = mCenterY - (mInnerRadius / 2);
        float centerRight = mCenterX + (mInnerRadius / 2);
        float centerBottom = mCenterY + (mInnerRadius / 2);
        mCenterRect.set(centerLeft, centerTop, centerRight, centerBottom);

        //进度条标志开始位置X轴坐标
        mProgressPointX = getXByProgress(mProgress);
        //进度条标志开始位置X轴坐标
        mProgressPointY = getYByProgress(mProgress);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mCenterX, mCenterY, mOuterRadius, mOuterPaint);
        canvas.drawCircle(mCenterX, mCenterY, mInnerRadius, mInnerPaint);
        canvas.drawArc(mDashRect, 0, 360, false, mDashPaint);

        //绘制Progress
        float progressLeft = mProgressPointX - mProgressRadius;
        float progressTop = mProgressPointY - mProgressRadius;
        float progressRight = mProgressPointX + mProgressRadius;
        float progressBottom = mProgressPointY + mProgressRadius;
        //进度标记坐标
        mProgressRect.set(progressLeft, progressTop, progressRight, progressBottom);
        canvas.drawBitmap(mProgressBitmap, null, mProgressRect, mProgressPaint);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        boolean up = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawProgress(x, y, up);
                currentMs = System.currentTimeMillis();
                System.out.println(currentMs);
                break;
            case MotionEvent.ACTION_MOVE:
                drawProgress(x, y, up);

                time += System.currentTimeMillis() - currentMs;
                System.out.println(time);
                if (time > 60000 & notfinished) {
                    notfinished = false;
                    mListener.achieved();

                }
                break;
            case MotionEvent.ACTION_UP:
                up = true;
                drawProgress(x, y, up);
                break;
        }
        return true;
    }

    private float getXByProgress(int progress) {
        float x = 0;
        float angle = (float) (2 * progress * Math.PI / 100);
        x = (float) (mCenterX + mOuterRadius * Math.cos(angle - Math.PI / 2));
        return x;
    }

    private float getYByProgress(int progress) {
        float y = 0;
        float angle = (float) (2 * progress * Math.PI / 100);
        y = (float) (mCenterY + mOuterRadius * Math.sin(angle - Math.PI / 2));
        return y;
    }

    /**
     * 绘制进度标记
     *
     * @param x  the x
     * @param y  the y
     * @param up the up
     */
    private void drawProgress(float x, float y, boolean up) {
        //触摸点到圆心的间距
        mDistance = (float) Math.sqrt(Math.pow((x - mCenterX), 2) + Math.pow((y - mCenterY), 2));

        if (mDistance < mOuterRadius + mOuterMargin && !up) {
            IS_PRESSED = true;

            mProgressPointX = x;
            mProgressPointY = y;

            float degrees = (float) ((float) ((Math.toDegrees(Math.atan2(x - mCenterX, mCenterY - y)) + 360.0)) % 360.0);
            if (degrees < 0) {
                degrees += 2 * Math.PI;
            }
        } else {
            IS_PRESSED = false;
            //计算进度标记在外圈轨道上X,Y的坐标,(ACTION_UP时自动回弹到外圈轨道对应角度的坐标)
            mProgressPointX = (float) (mCenterX + mOuterRadius * Math.cos(Math.atan2(x - mCenterX, mCenterY - y) - (Math.PI / 2)));
            mProgressPointY = (float) (mCenterY + mOuterRadius * Math.sin(Math.atan2(x - mCenterX, mCenterY - y) - (Math.PI / 2)));

        }
        invalidate();
    }

    /**
     * 设置圆弧的角度
     *
     * @param angle
     */
    /**
     * 获取progress
     *
     * @return
     */
    public int getProgress() {
        return mProgress;
    }

    /**
     * 获取progress
     *
     * @return
     */
    public void setProgress(int progress) {
        mProgress = progress;
    }

    /**
     * 获取progress标记点到圆心的距离
     *
     * @return
     */
    private float getDistance() {
        return mDistance;
    }

    public void setProgressChangeListener(OnProgressChangeListener listener) {
        mOnProgressChangeListener = listener;
    }

    interface OnProgressChangeListener {
        /**
         * 进度改变的回调方法
         *
         * @param progress 当前进度
         * @param distance 当前进度坐标点到圆心的距离
         */
        void onProgressChange(int progress, float distance);
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
