package com.np.a05shadow.demo7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.np.a05shadow.R;

/**
 * package: com.np.a05shadow.demo7
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class ComposeShaderView extends View {

    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 位图渐变
        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        BitmapShader src = new BitmapShader(bitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        // 线性渐变
        LinearGradient dst = new LinearGradient(0, 0, getMeasuredWidth(), 0,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        // 混合渐变
//        ComposeShader cs = new ComposeShader(src, dst, PorterDuff.Mode.DST_ATOP);
        ComposeShader cs = new ComposeShader(src, dst,
                new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        paint.setShader(cs);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
    }
}
