package com.np.a05shadow.demo9.demo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.np.a05shadow.R;

/**
 * package: com.np.a05shadow.demo9
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class CirclePhotoView extends View {

    private Bitmap bitmapCat;
    private Bitmap bitmapCircle;
    private Paint paint;

    public CirclePhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapCat = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
        int minWidth = Math.min(bitmapCat.getWidth(), bitmapCat.getHeight());

        // 以图片的最短边长创建一个 位图
        bitmapCircle = Bitmap.createBitmap(minWidth, minWidth, Bitmap.Config.ARGB_8888);
        // 将 位图 与 Canvas 对象关联 并 在该位图上绘制一个圆
        Canvas canvasCircle = new Canvas(bitmapCircle);
        canvasCircle.drawCircle(minWidth / 2, minWidth / 2, minWidth / 2, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapCat, 0, 0, null);
        // 定义位图运算模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapCircle, 0, 0, paint);
    }
}
