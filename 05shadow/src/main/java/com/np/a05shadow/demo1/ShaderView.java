package com.np.a05shadow.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo1
 * des    :
 * author : NingPan on 2017/3/14.
 */

public class ShaderView extends View {

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(60);

        // 设置 View 的 layer type 类型为 LAYER_TYPE_SOFTWARE
        setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        // 设置阴影, 当偏移量足够下时,其实就是发光效果.
        paint.setShadowLayer(10, 1, 1, Color.RED);
        canvas.drawText("Shader设置文字发光", 100, 100, paint);

        canvas.translate(0, 200);
        paint.setShadowLayer(10, 5, 15, Color.BLUE);
        canvas.drawText("Shader设置文字阴影", 100, 100, paint);
    }
}
