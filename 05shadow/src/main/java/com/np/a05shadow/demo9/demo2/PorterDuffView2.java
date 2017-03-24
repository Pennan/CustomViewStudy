package com.np.a05shadow.demo9.demo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo9.demo2
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class PorterDuffView2 extends View {

    private Bitmap dstBitmap, srcBitmap, mBitmap;
    private Canvas dstCanvas, srcCanvas, mCanvas;
    private Paint dstPaint, srcPaint, mPaint;

    public PorterDuffView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        dstBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        srcBitmap = dstBitmap.copy(Bitmap.Config.ARGB_8888, true);
        mBitmap = Bitmap.createBitmap(450, 450, Bitmap.Config.ARGB_8888);

        dstCanvas = new Canvas(dstBitmap);
        srcCanvas = new Canvas(srcBitmap);
        mCanvas = new Canvas(mBitmap);

        dstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstPaint.setColor(Color.YELLOW);
        srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        srcPaint.setColor(Color.BLUE);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        dstCanvas.drawCircle(150, 150, 150, dstPaint);
        srcCanvas.drawRect(0, 0, 300, 300, srcPaint);

        // 创建图层:图层的大小要和需要显示出来的位图相匹配
//        int layer = mCanvas.saveLayer(150, 150, 450, 450, mPaint, Canvas.ALL_SAVE_FLAG);
        int layer = mCanvas.saveLayer(0, 0, 450, 450, mPaint, Canvas.ALL_SAVE_FLAG);
        mCanvas.drawBitmap(dstBitmap, 0, 0, null);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        mCanvas.drawBitmap(srcBitmap, 150, 150, mPaint);

        // 图层出栈和清除位图运算效果
        mPaint.setXfermode(null);
        mCanvas.restoreToCount(layer);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }
}
