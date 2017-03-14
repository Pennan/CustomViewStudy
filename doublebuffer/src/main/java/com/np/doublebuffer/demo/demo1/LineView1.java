package com.np.doublebuffer.demo.demo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * package: com.np.doublebuffer.demo.demo1
 * des    :
 * author : NingPan on 2017/3/12.
 */

public class LineView1 extends View {

    /** 上一个点的坐标 */
    private int preX;
    private int preY;

    /** Bitmap 缓存区 */
    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;
    private Paint bitmapPaint;

    public LineView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setColor(Color.BLUE);
        bitmapPaint.setStyle(Paint.Style.STROKE);
        bitmapPaint.setStrokeWidth(5);
    }

    /** 当 View 大小发现改变时调用该方法，setFrame()  */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        bitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmapBuffer);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currX = (int) event.getX();
        int currY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = currX;
                preY = currY;
                break;
            case MotionEvent.ACTION_MOVE:
                bitmapCanvas.drawLine(preX, preY, currX, currY, bitmapPaint);
                invalidate();

                preX = currX;
                preY = currY;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }
}
