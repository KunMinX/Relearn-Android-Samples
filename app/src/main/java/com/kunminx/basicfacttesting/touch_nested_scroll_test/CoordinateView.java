package com.kunminx.basicfacttesting.touch_nested_scroll_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.kunminx.utils.SizeUtils;

/**
 * @author xmj
 * @date 2020/3/10
 */
public class CoordinateView extends View {

    private Paint mPaint = new Paint();
    private Paint mPaint1 = new Paint();
    private RectF mRectF = new RectF();
    private int mTextSize = SizeUtils.sp2px(16);
    private int mRadius = SizeUtils.sp2px(12);
    private int mDownX;
    private int mDownY;

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(getLeft() + " , " + getTop(), 50, 100, mPaint);
        canvas.drawText(getRight() + " , " + getTop(), getWidth() - 400, 100, mPaint);
        canvas.drawText(getLeft() + " , " + getBottom(), 50, getHeight() - 100, mPaint);
        canvas.drawText(getRight() + " , " + getBottom(), getWidth() - 400, getHeight() - 100, mPaint);

        mPaint1.setColor(Color.GRAY);
        mPaint1.setStyle(Paint.Style.STROKE);
        mRectF.set(10, 10, getWidth() - 10, getHeight() - 10);
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint1);

        Log.d("TAG", getLeft() + " " + getRight() + " " + getTop() + " " + getBottom());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) (event.getRawX() + 0.5f);
        int y = (int) (event.getRawY() + 0.5f);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dx = x - mDownX;
                int dy = y - mDownY;
                if (Math.abs(dx) > 8 || Math.abs(dy) > 8) {
                    int orientation = getOrientation(dx, dy);
                    switch (orientation) {
                        case 'r':


                            return true;
                        case 'l':
                            return true;
                        case 't':
                            return false;
                        case 'b':
                            return false;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                /*float dx = x - mDownX;
                float dy = y - mDownY;
                if (Math.abs(dx) > 8 && Math.abs(dy) > 8) {
                    int orientation = getOrientation(dx, dy);
                    switch (orientation) {
                        case 'r':
                            return true;
                        case 'l':
                            return true;
                        case 't':
                            return false;
                        case 'b':
                            return false;
                    }
                }*/
                break;
        }

        return false;
    }

    private int getOrientation(int dx, int dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? 'r' : 'l';
        } else {
            return dy > 0 ? 'b' : 't';
        }
    }
}
