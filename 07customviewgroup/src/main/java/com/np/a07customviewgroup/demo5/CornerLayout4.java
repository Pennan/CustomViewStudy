package com.np.a07customviewgroup.demo5;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.np.a07customviewgroup.R;

/**
 * package: com.np.a07customviewgroup.demo2
 * des    :
 * author : NingPan on 2017/3/21.
 */

public class CornerLayout4 extends ViewGroup {

    public CornerLayout4(Context context) {
        this(context, null);
    }

    public CornerLayout4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLayout4(Context context, AttributeSet attrs, int defStyleAttr) {
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

    /**
     * 测量容器高度
     */
    private int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int aHeight, bHeight, cHeight, dHeight;
            aHeight = bHeight = cHeight = dHeight = 0;

            int aHMargin, bHMargin, cHMargin, dHMargin;
            aHMargin = bHMargin = cHMargin = dHMargin = 0;

            for (int i = 0; i < this.getChildCount(); i++) {
                View view = this.getChildAt(i);
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                if (i == 0) {
                    aHeight = this.getChildAt(i).getMeasuredHeight();
                    aHMargin = params.topMargin + params.bottomMargin;
                }
                if (i == 1) {
                    bHeight = this.getChildAt(i).getMeasuredHeight();
                    bHMargin = params.topMargin + params.bottomMargin;
                }
                if (i == 2) {
                    cHeight = this.getChildAt(i).getMeasuredHeight();
                    cHMargin = params.topMargin + params.bottomMargin;
                }
                if (i == 3) {
                    dHeight = this.getChildAt(i).getMeasuredHeight();
                    dHMargin = params.topMargin + params.bottomMargin;
                }
            }
            height = Math.max(aHeight, bHeight) + Math.max(cHeight, dHeight)
                    + getPaddingTop() + getPaddingBottom()
                    + Math.max(aHMargin, bHMargin) + Math.max(cHMargin, dHMargin);
        }
        return height;
    }

    /**
     * 测量容器宽度
     */
    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int aWidth, bWidth, cWidth, dWidth;
            aWidth = bWidth = cWidth = dWidth = 0;

            int aWMargin, bWMargin, cWMargin, dWMargin;
            aWMargin = bWMargin = cWMargin = dWMargin = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                View view = this.getChildAt(i);
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                if (i == 0) {
                    aWidth = view.getMeasuredWidth();
                    aWMargin = params.leftMargin + params.rightMargin;
                }
                if (i == 1) {
                    bWidth = view.getMeasuredWidth();
                    bWMargin = params.leftMargin + params.rightMargin;
                }
                if (i == 2) {
                    cWidth = view.getMeasuredWidth();
                    cWMargin = params.leftMargin + params.rightMargin;
                }
                if (i == 3) {
                    dWidth = view.getMeasuredWidth();
                    dWMargin = params.leftMargin + params.rightMargin;
                }
            }
            width = Math.max(aWidth, cWidth) + Math.max(bWidth, dWidth)
                    + getPaddingLeft() + getPaddingLeft()
                    + Math.max(aWMargin, cWMargin) + Math.max(bWMargin, dWMargin);
        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        for (int i = 0; i < this.getChildCount(); i++) {
            View view = getChildAt(i);
            PositionLayoutParams params = (PositionLayoutParams) view.getLayoutParams();
            if (i == 0 && params.position == PositionLayoutParams.NONE
                    || params.position == PositionLayoutParams.LEFT_TOP) // 定位到左上角
                view.layout(paddingLeft + params.leftMargin, paddingTop + params.topMargin,
                        view.getMeasuredWidth() + paddingLeft + params.leftMargin,
                        view.getMeasuredHeight() + paddingTop + params.topMargin);
            if (i == 1 && params.position == PositionLayoutParams.NONE
                    || params.position == PositionLayoutParams.RIGHT_TOP) // 定位到右上角
                view.layout(this.getMeasuredWidth() - view.getMeasuredWidth()
                        - paddingRight - params.rightMargin,
                        paddingTop + params.topMargin,
                        this.getMeasuredWidth() - paddingRight - params.rightMargin,
                        view.getMeasuredHeight() + paddingTop + params.topMargin);
            if (i == 2 && params.position == PositionLayoutParams.NONE
                    || params.position == PositionLayoutParams.LEFT_BOTTOM) // 定位到左下角
                view.layout(paddingLeft + params.leftMargin,
                        this.getMeasuredHeight() - view.getMeasuredHeight()
                                - paddingBottom - params.bottomMargin,
                        view.getMeasuredWidth() + paddingLeft + params.leftMargin,
                        this.getMeasuredHeight() - paddingBottom - params.bottomMargin);
            if (i == 3 && params.position == PositionLayoutParams.NONE
                    || params.position == PositionLayoutParams.RIGHT_BOTTOM) // 定位到右下角
                view.layout(this.getMeasuredWidth() - view.getMeasuredWidth()
                        - paddingRight - params.rightMargin,
                        this.getMeasuredHeight() - view.getMeasuredHeight()
                                - paddingBottom - params.bottomMargin,
                        this.getMeasuredWidth() - paddingRight - params.rightMargin,
                        this.getMeasuredHeight() - paddingBottom - params.bottomMargin);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PositionLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new PositionLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new PositionLayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    /** 自定义 LayoutParams */
    public static class PositionLayoutParams extends MarginLayoutParams {

        private static final int LEFT_TOP = 0;
        private static final int RIGHT_TOP = 1;
        private static final int LEFT_BOTTOM = 2;
        private static final int RIGHT_BOTTOM = 3;
        private static final int NONE = -1;

        private int position;

        public PositionLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            // 读取 layout_position 自定义属性
            TypedArray ta = c.obtainStyledAttributes(attrs, R.styleable.CornerLayout4);
            position = ta.getInt(R.styleable.CornerLayout4_layout_position, NONE);
            ta.recycle();
        }

        public PositionLayoutParams(int width, int height) {
            super(width, height);
        }

        public PositionLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public PositionLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
