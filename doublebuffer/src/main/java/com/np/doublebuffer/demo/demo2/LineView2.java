package com.np.doublebuffer.demo.demo2;

import android.content.Context;
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

public class LineView2 extends View {

    private Path path;
    private int preX, preY;
    private Paint paint;

    public LineView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
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
                path.quadTo(preX, preY, currX, currY);
                invalidate();

                preX = currX;
                preY = currY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
