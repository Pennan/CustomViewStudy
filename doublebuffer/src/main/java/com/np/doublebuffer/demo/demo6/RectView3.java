package com.np.doublebuffer.demo.demo6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * package: com.np.doublebuffer.demo.demo4
 * des    :
 * author : NingPan on 2017/3/13.
 */

public class RectView3 extends View {

    /** 记录手指按下时的坐标 */
    private int firstX, firstY;
    private Path path;
    private Paint paint;

    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;

    public RectView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (bitmapBuffer == null) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            bitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmapCanvas = new Canvas(bitmapBuffer);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currX = (int) event.getX();
        int currY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();

                firstX = currX;
                firstY = currY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 手指移动的时候，要先清除前一次的绘制结果
                path.reset();
                
                if (firstX < currX && firstY < currY) {
                    // ↘ 方向
                    path.addRect(firstX, firstY, currX, currY, Path.Direction.CW);
                } else if (firstX > currX && firstY > currY) {
                    // ↖ 方向
                    path.addRect(currX, currY, firstX, firstY, Path.Direction.CW);
                } else if (firstX < currX && firstY > currY) {
                    // ↗ 方向
                    path.addRect(firstX, currY, currX, firstY, Path.Direction.CW);
                } else if (firstX > currX && firstY < currY) {
                    // ↙ 方向
                    path.addRect(currX, firstY, firstX, currY, Path.Direction.CW);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                // 保存 Path 对象绘制的记录
                bitmapCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }
}
