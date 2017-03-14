package com.np.doublebuffer.demo.demo3;

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
 * package: com.np.doublebuffer.demo.demo2
 * des    :
 * author : NingPan on 2017/3/13.
 */

public class LineView3 extends View {

    private Path path;
    private int preX, preY;
    private Paint paint;

    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;

    public LineView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

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
                path.reset(); // 绘图第二条曲线的时候重置 Path 对象
                path.moveTo(currX, currY);

                preX = currX;
                preY = currY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 使用贝塞尔曲线进行绘图时，需要一个起点 (preX, preY),
                // 终点 (currX, currY), 一个控制点
                // ((preX + currX) / 2), (preY + currY) / 2)
                int controlX = (preX + currX) / 2;
                int controlY = (preY + currY) / 2;
                // 手指移动过程中只显示绘制过程
                // path.quadTo(preX, preY, currX, currY);
                path.quadTo(controlX, controlY, currX, currY);
                invalidate();

                preX = currX;
                preY = currY;
                break;
            case MotionEvent.ACTION_UP:
                // Path 每绘制一条曲线完成后都将它保存在 bitmapCanvas 对象上,
                // 然后再将 bitmapBuffer 绘制到 Canvas 上,这样就起到了一个缓存效果.
                bitmapCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }
}
