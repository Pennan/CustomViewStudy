package com.np.a05shadow.demo8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo8
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class GradientMatrixView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Matrix mMatrix = new Matrix();
    private Shader mShader;
    private int mDegree;

    public GradientMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mShader = new SweepGradient(0, 0,
                new int[] {Color.BLUE, Color.RED, Color.GREEN, Color.BLUE}, null);
        paint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 设置背景颜色 并且 将坐标原点平移至屏幕中心
        canvas.drawColor(Color.GRAY);
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        mMatrix.setRotate(mDegree, 0, 0);
        mShader.setLocalMatrix(mMatrix);
        mDegree += 3; // 没 invalidate() 一次, Shader 旋转角度 + 3.

        if (mDegree >= 360) {
            mDegree = 0;
        }
        canvas.drawCircle(0, 0, 380, paint);
        invalidate();
    }
}
