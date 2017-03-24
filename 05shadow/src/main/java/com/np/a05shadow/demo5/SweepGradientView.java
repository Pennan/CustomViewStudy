package com.np.a05shadow.demo5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo5
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class SweepGradientView extends View {

    public SweepGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 两种颜色的 扫描渐变
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        SweepGradient sg = new SweepGradient(200, 200, Color.RED, Color.BLUE);
        paint.setShader(sg);
        canvas.drawRect(0, 0, 400, 400, paint);

        canvas.translate(0, 450);
        // 多种颜色的 扫描渐变
        sg = new SweepGradient(200, 200,
                new int[] {Color.BLUE, Color.GREEN, Color.RED, Color.BLUE}, null);
        paint.setShader(sg);
        canvas.drawCircle(200, 200, 200, paint);
    }
}
