package com.np.a05shadow.demo6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.np.a05shadow.R;

/**
 * package: com.np.a05shadow.demo6
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class BitmapShaderView extends View {

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = BitmapFactory.
                decodeResource(getResources(), R.mipmap.ic_launcher);
        BitmapShader bs = new BitmapShader(bitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        paint.setShader(bs);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
    }
}
