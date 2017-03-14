package com.np.doublebuffer.demo.demo7.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.np.doublebuffer.demo.demo7.BitmapBuffer;
import com.np.doublebuffer.demo.demo7.SystemParams;
import com.np.doublebuffer.demo.demo7.drawer.LineDrawer;
import com.np.doublebuffer.demo.demo7.drawer.ShapeDrawer;

/**
 * package: com.np.doublebuffer.demo.demo7.view
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class DrawerView extends View {

    /** 图形绘制器 */
    private ShapeDrawer shapeDrawer;

    public DrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 默认画线条
        shapeDrawer = new LineDrawer(this);
    }

    public void setShapeDrawer(ShapeDrawer shapeDrawer) {
        this.shapeDrawer = shapeDrawer;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        SystemParams.areaWidth = getMeasuredWidth();
        SystemParams.areaHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (SystemParams.isRedo) {
            // 撤销
            canvas.drawBitmap(BitmapBuffer.getInstance().getBitmap(), 0, 0, null);
            SystemParams.isRedo = false;
        } else {
            shapeDrawer.onDraw(canvas);
        }
        // 执行逻辑方法
        shapeDrawer.logic();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return shapeDrawer.onTouchEvent(event);
    }
}
