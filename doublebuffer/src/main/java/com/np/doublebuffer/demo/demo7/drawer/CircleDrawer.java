package com.np.doublebuffer.demo.demo7.drawer;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * package: com.np.doublebuffer.demo.demo7.drawer
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class CircleDrawer extends ShapeDrawer {


    public CircleDrawer(View view) {
        super(view);
    }

    @Override
    public void onDraw(Canvas viewCanvas) {
        super.onDraw(viewCanvas);
//        viewCanvas.drawCi
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    @Override
    public void logic() {

    }
}
