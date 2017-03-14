package com.np.doublebuffer.demo.demo7.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.np.doublebuffer.demo.demo7.AttributesTool;
import com.np.doublebuffer.demo.demo7.BitmapBuffer;

/**
 * package: com.np.doublebuffer.demo.demo7.drawer
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class LineDrawer extends ShapeDrawer {

    private int preX, preY;
    private int endX, endY;

    public LineDrawer(View view) {
        super(view);
    }

    @Override
    public void onDraw(Canvas viewCanvas) {
        super.onDraw(viewCanvas);
        drawShape(viewCanvas, preX, preY, endX, endY);
    }

    private void drawShape(Canvas viewCanvas, int preX, int preY,
                           int currentX, int currentY) {
        Paint paint = AttributesTool.getInstance().getPaint();
        viewCanvas.drawLine(preX, preY, currentX, currentY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                endX = currentX;
                endY = currentY;

                // 将最终的线条绘制在缓存区
                Canvas canvas = BitmapBuffer.getInstance().getCanvas();
                drawShape(canvas, preX, preY, endX, endY);
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
