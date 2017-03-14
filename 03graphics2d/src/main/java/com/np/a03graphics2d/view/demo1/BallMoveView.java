package com.np.a03graphics2d.view.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 模拟一个球，循环左右滚动，遇到 屏幕边界则反弹往另一边滚动
 */
public class BallMoveView extends View {
    private int x; // 圆球的 x 位置
    private int y = 100; // 圆球的默认高度
    private static final int RADIUS = 25;
    private boolean direction; // 圆球滚动的方向
    private int screenWidth; // 屏幕的宽度
    private Paint mPaint;

    public BallMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenWidth = getMeasuredWidth();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, RADIUS, mPaint);
        if (x < RADIUS) {
            direction = true;
        }
        if (x >= screenWidth - RADIUS) {
            direction = false;
        }
        x = direction ? x + 5 : x - 5;
    }
}
