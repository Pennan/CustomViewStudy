package com.np.a07customviewgroup.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * package: com.np.a07customviewgroup.demo2
 * des    :
 * author : NingPan on 2017/3/21.
 */

public class CornerLayout extends ViewGroup {

    public CornerLayout(Context context) {
        this(context, null);
    }

    public CornerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 首先测量子组件
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 然后测量自己
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
    }

    /** 测量容器高度 */
    private int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int aHeight = 0;
            int bHeight = 0;
            int cHeight = 0;
            int dHeight = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                if (i == 0)
                    aHeight = this.getChildAt(i).getMeasuredHeight();
                if (i == 1)
                    bHeight = this.getChildAt(i).getMeasuredHeight();
                if (i == 2)
                    cHeight = this.getChildAt(i).getMeasuredHeight();
                if (i == 3)
                    dHeight = this.getChildAt(i).getMeasuredHeight();
            }
            height = Math.max(aHeight, bHeight) + Math.max(cHeight, dHeight);
        }
        return height;
    }

    /** 测量容器宽度 */
    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int aWidth = 0;
            int bWidth = 0;
            int cWidth = 0;
            int dWidth = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                if (i == 0)
                    aWidth = this.getChildAt(i).getMeasuredWidth();
                if (i == 1)
                    bWidth = this.getChildAt(i).getMeasuredWidth();
                if (i == 2)
                    cWidth = this.getChildAt(i).getMeasuredWidth();
                if (i == 3)
                    dWidth = this.getChildAt(i).getMeasuredWidth();
            }
            width = Math.max(aWidth, cWidth) + Math.max(bWidth, dWidth);
        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View view = getChildAt(i);
            if (i == 0) // 定位到左上角
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            if (i == 1) // 定位到右上角
                view.layout(this.getMeasuredWidth() - view.getMeasuredWidth(),
                        0, this.getMeasuredWidth(), view.getMeasuredHeight());
            if (i == 2) // 定位到左下角
                view.layout(0, this.getMeasuredHeight() - view.getMeasuredHeight(),
                        view.getMeasuredWidth(), this.getMeasuredHeight());
            if (i == 3) // 定位到右下角
                view.layout(this.getMeasuredWidth() - view.getMeasuredWidth(),
                        this.getMeasuredHeight() - view.getMeasuredHeight(),
                        this.getMeasuredWidth(), this.getMeasuredHeight());
        }
    }
}
