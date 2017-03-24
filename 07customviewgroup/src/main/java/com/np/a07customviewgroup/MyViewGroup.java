package com.np.a07customviewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * package: com.np.a07customviewgroup
 * des    :
 * author : NingPan on 2017/3/21.
 */

public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    // 测量容器的尺寸
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
    }
    // 确定每个子组件的位置 changed：是否有新的尺寸或位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layout(l, t, r, b);
    }
    // 绘制容器
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dispatchDraw(canvas);
    }
}
