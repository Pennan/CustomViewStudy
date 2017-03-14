package com.np.doublebuffer.demo.demo7.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import com.np.doublebuffer.demo.demo7.AttributesTool;
import com.np.doublebuffer.demo.demo7.BitmapBuffer;

/**
 * package: com.np.doublebuffer.demo.demo7.drawer
 * des    :
 * author : NingPan on 2017/3/13.
 */

public class RectDrawer extends ShapeDrawer {

    private int currentX;
    private int currentY;
    private int firstX;
    private int firstY;

    protected Path path;

    public RectDrawer(View view) {
        super(view);
        path = new Path();
    }

    @Override
    public void onDraw(Canvas viewCanvas) {
        super.onDraw(viewCanvas);
        drawShape(viewCanvas, firstX, firstY, currentX, currentY);
    }

    protected void drawShape(Canvas viewCanvas, int firstX, int firstY,
                           int currentX, int currentY) {
        Paint paint = AttributesTool.getInstance().getPaint();
        /*if (firstX < currentX && firstY < currentY) {
            // ↘
            path.addRect(firstX, firstY, currentX, currentY, Path.Direction.CW);
        } else if (firstX > currentX && firstY > currentY) {
            // ↖
            path.addRect(currentX, currentY, firstX, firstY, Path.Direction.CW);
        } else if (firstX > currentX && firstY < currentY) {
            // ↙
            path.addRect(currentX, firstY, firstX, currentY, Path.Direction.CW);
        }  else if (firstX < currentX && firstY > currentY) {
            // ↗
            path.addRect(firstX, currentY, currentX, firstY, Path.Direction.CW);
        }
        viewCanvas.drawPath(path, paint);*/

        if (firstX < currentX && firstY < currentY) {
            // ↘
            viewCanvas.drawRect(firstX, firstY, currentX, currentY, paint);
        } else if (firstX > currentX && firstY > currentY) {
            // ↖
            viewCanvas.drawRect(currentX, currentY, firstX, firstY, paint);
        } else if (firstX > currentX && firstY < currentY) {
            // ↙
            viewCanvas.drawRect(currentX, firstY, firstX, currentY, paint);
        }  else if (firstX < currentX && firstY > currentY) {
            // ↗
            viewCanvas.drawRect(firstX, currentY, currentX, firstY, paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = (int) event.getX();
        currentY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 使用 Path 绘制矩形的时候，必须时刻 reset() Path
                path.reset();

                firstX = currentX;
                firstY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 使用 Path 绘制矩形的时候，必须时刻 reset() Path
                path.reset();

                getView().invalidate();
                break;
            case MotionEvent.ACTION_UP:
                // 将最终的矩形绘制在缓存区
                Canvas canvas = BitmapBuffer.getInstance().getCanvas();
                drawShape(canvas, firstX, firstY, currentX, currentY);
                getView().invalidate();
                // 保存到撤销栈中
                BitmapBuffer.getInstance().pushBitmap();
                break;
        }
        return true;
    }

    @Override
    public void logic() {

    }
}
