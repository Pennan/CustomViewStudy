package com.np.a05shadow.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo2
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class LinearGradientView extends View {

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);

        Rect rect1 = new Rect(50, 50, 450, 450);
        LinearGradient linearGradient = new LinearGradient(
                rect1.left, rect1.top, rect1.right, rect1.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(rect1, paint);

        canvas.translate(0, 500);
        // 放大渐变矩形
        Rect rect2 = new Rect(rect1);
        rect2.inset(-100, -100);
        linearGradient = new LinearGradient(
                rect2.left, rect2.top, rect2.right, rect2.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(rect1, paint);

        canvas.translate(0, 500);
        // 缩小渐变矩形
        Rect rect3 = new Rect(rect1);
        rect3.inset(100, 100);
        linearGradient = new LinearGradient(
                rect3.left, rect3.top, rect3.right, rect3.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(rect1, paint);
    }
}
