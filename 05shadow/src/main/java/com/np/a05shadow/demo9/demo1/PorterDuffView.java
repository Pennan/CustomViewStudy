package com.np.a05shadow.demo9.demo1;

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
 * package: com.np.a05shadow.demo9
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class PorterDuffView extends View {

    private Bitmap dstBitmap, srcBitmap, mBitmap;
    private Canvas dstCanvas, srcCanvas, mCanvas;
    private Paint dstPaint, srcPaint, mPaint;

    public PorterDuffView(Context context, AttributeSet attrs) {
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
        // 分别给 第一个位图和第二个位图 绘制圆和矩形.
        dstCanvas.drawCircle(150, 150, 150, dstPaint);
        srcCanvas.drawRect(0, 0, 300, 300, srcPaint);

        // 将 画圆的位图 绘制 第三个 位图上
        mCanvas.drawBitmap(dstBitmap, 0, 0, null);
        // 定义位图运算模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        // 将 画矩形的位图 绘制 第三个 位图上,
        // 并将定义了位图运算模式的 Paint 作为参数传入
        mCanvas.drawBitmap(srcBitmap, 150, 150, mPaint);

        mPaint.setXfermode(null); // 清除运算效果
        // 将 第三个位图 绘制到 View 的 canvas 上
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }
}
