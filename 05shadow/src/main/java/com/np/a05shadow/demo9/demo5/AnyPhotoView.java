package com.np.a05shadow.demo9.demo5;

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
 * package: com.np.a05shadow.demo9.demo5
 * des    :
 * author : NingPan on 2017/3/16.
 */

public class AnyPhotoView extends View {

    private Bitmap bitmapHead;
    private Bitmap bitmapMask;
    private Paint paint;
    private static final int OFFSET = 100;

    public AnyPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapHead = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
        Bitmap launcher = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_robot);
        bitmapMask = launcher.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int layer = canvas.saveLayer(OFFSET, OFFSET, bitmapMask.getWidth() + OFFSET,
                bitmapMask.getHeight() + OFFSET, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmapHead, 0, 0, null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapMask, OFFSET, OFFSET, paint);
        canvas.restoreToCount(layer);
    }
}
