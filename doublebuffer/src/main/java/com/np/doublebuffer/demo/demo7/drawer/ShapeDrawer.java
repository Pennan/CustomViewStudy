package com.np.doublebuffer.demo.demo7.drawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.np.doublebuffer.demo.demo7.BitmapBuffer;

/**
 * package: com.np.doublebuffer.demo.demo7.drawer
 * des    :
 * author : NingPan on 2017/3/13.
 */

public abstract class ShapeDrawer {
    private View view;

    public ShapeDrawer(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    }

    /**
     * 用于绘图
     * @param viewCanvas 用于展示结果的画布
     */
    public void onDraw(Canvas viewCanvas) {
        Bitmap bitmap = BitmapBuffer.getInstance().getBitmap();
        viewCanvas.drawBitmap(bitmap, 0, 0, null);
    }

    /** 用于相应触摸事件 */
    public abstract boolean onTouchEvent(MotionEvent event);

    /** 绘图的逻辑 */
    public abstract void logic();
}
