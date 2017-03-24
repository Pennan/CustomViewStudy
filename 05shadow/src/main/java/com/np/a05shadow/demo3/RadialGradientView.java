package com.np.a05shadow.demo3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo3
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class RadialGradientView extends View {

    public RadialGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);

        RadialGradient radialGradient = new RadialGradient(200, 200, 200,
                Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        paint.setShader(radialGradient);
        canvas.drawRect(0, 0, 400, 400, paint);

        radialGradient = new RadialGradient(800, 200, 200,
                Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        paint.setShader(radialGradient);
        canvas.drawCircle(800, 200, 200, paint);
    }
}
