package com.np.doublebuffer.demo.demo7.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import com.np.doublebuffer.demo.demo7.AttributesTool;

/**
 * package: com.np.doublebuffer.demo.demo7.drawer
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class OvalDrawer extends RectDrawer {


    public OvalDrawer(View view) {
        super(view);
    }

    @Override
    protected void drawShape(Canvas viewCanvas, int firstX, int firstY, int currentX, int currentY) {
        Paint paint = AttributesTool.getInstance().getPaint();
        if (firstX < currentX && firstY < currentY) {
            // ↘
            path.addOval(new RectF(firstX, firstY, currentX, currentY), Path.Direction.CW);
        } else if (firstX > currentX && firstY > currentY) {
            // ↖
            path.addOval(new RectF(currentX, currentY, firstX, firstY), Path.Direction.CW);
        } else if (firstX > currentX && firstY < currentY) {
            // ↙
            path.addOval(new RectF(currentX, firstY, firstX, currentY), Path.Direction.CW);
        }  else if (firstX < currentX && firstY > currentY) {
            // ↗
            path.addOval(new RectF(firstX, currentY, currentX, firstY), Path.Direction.CW);
        }
        viewCanvas.drawPath(path, paint);

        /*if (firstX < currentX && firstY < currentY) {
            // ↘
            viewCanvas.drawOval(new RectF(firstX, firstY, currentX, currentY), paint);
        } else if (firstX > currentX && firstY > currentY) {
            // ↖
            viewCanvas.drawOval(new RectF(currentX, currentY, firstX, firstY), paint);
        } else if (firstX > currentX && firstY < currentY) {
            // ↙
            viewCanvas.drawOval(new RectF(currentX, firstY, firstX, currentY), paint);
        }  else if (firstX < currentX && firstY > currentY) {
            // ↗
            viewCanvas.drawOval(new RectF(firstX, currentY, currentX, firstY), paint);
        }*/
    }
}
