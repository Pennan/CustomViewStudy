package com.np.a07customviewgroup.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * package: com.np.a07customviewgroup.demo1
 * des    :
 * author : NingPan on 2017/3/21.
 */

public class SizeViewGroup extends ViewGroup {

    public SizeViewGroup(Context context) {
        this(context, null);
    }

    public SizeViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SizeViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 添加一个 TextView 到该容器中
        TextView textView = new TextView(context);
        ViewGroup.LayoutParams params = new LayoutParams(200, 200);
        textView.setText("Android");
        textView.setBackgroundColor(Color.BLUE);
        // 将当前组件添加到 当前容器中
        this.addView(textView, params);
        // 设置当前容器的的背景颜色为 透明: 可以绘制不规则图形的组件
        this.setBackgroundColor(Color.alpha(255));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 首先测量子组件
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 然后测量自身的大小，这里直接写死为 500 * 500
        this.setMeasuredDimension(500, 500);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childAt = getChildAt(0);
        childAt.layout(50, 50, 200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 为当前容器绘制一个 红色边框
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        rectF.inset(2, 2);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        canvas.drawRoundRect(rectF, 10, 10, paint);
    }
}
