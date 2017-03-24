package com.np.a07customviewgroup.demo6;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * package: com.np.a07customviewgroup.demo6
 * des    :
 * author : NingPan on 2017/3/22.
 */

public class FlowLayout extends ViewGroup {

    private static final String TAG = FlowLayout.class.getSimpleName();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
    }

    private int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int width = getMeasuredWidth(); // 容器的宽度
            int childCount = getChildCount();

            int maxViewHeight = 0; // 当前行的子组件的最大高度
            int maxLineWidth = 0; // 当前行的子组件的总宽度

            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                maxLineWidth += child.getMeasuredWidth();
                maxViewHeight = Math.max(maxViewHeight,
                        child.getMeasuredHeight());
                // 判断是否换行
                if (i < childCount -1
                        && maxLineWidth + getChildAt(i + 1).getMeasuredWidth()
                        > width - getPaddingLeft() - getPaddingRight()) {
                    // 当前行的子组件宽度如果超出容器宽度,则换行
                    height += maxViewHeight;
                    maxViewHeight = 0;
                    maxLineWidth = 0;
                } else if (i == childCount - 1) {
                    // 已经遍历到最后一个
                    height += maxViewHeight;
                }
            }
        }
        // 加上容器的 padding
        height += getPaddingTop() + getPaddingBottom();
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        Log.i(TAG, "size = " + size);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            // 获取所有子组件占的总宽度
            int childrenWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                int childWidth = view.getMeasuredWidth();
                // 单个子组件的宽度不能大于容器宽度
                if (childWidth > size) {
                    throw new IllegalStateException("Sub view is too large");
                }
                childrenWidth += childWidth;
            }
            if (childrenWidth > size) {
                width = size;
            } else {
                width = childrenWidth;
            }
            // 计算 容器的 padding
            width += getPaddingLeft() + getPaddingRight();
        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth(); // 容器宽度
        int maxViewHeight = 0; // 当前行的子组件的最大高度
        int maxLineWidth = 0; // 当前行的子组件的总宽度
        int totalHeight = 0; // 总高度
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            // 判断是否要换行显示
            if (maxLineWidth + getChildAt(i).getMeasuredWidth()
                    > width - getPaddingLeft() - getPaddingRight()) {
                // 换行后累计显示的行的总高度
                totalHeight += maxViewHeight;
                // 新起的一行,新行的已占宽度和最大高度重置为 0.
                maxViewHeight = 0;
                maxLineWidth = 0;
            }

            // 定位子组件,考虑 父容器的 padding
            child.layout(maxLineWidth + getPaddingLeft(), totalHeight + getPaddingTop(),
                    maxLineWidth + child.getMeasuredWidth() + getPaddingLeft(),
                    totalHeight + child.getMeasuredHeight() + getPaddingTop());

            // 累加当前行的宽度
            maxLineWidth += child.getMeasuredWidth();
            // 获取当前行的最大高度
            maxViewHeight = Math.max(maxViewHeight, child.getMeasuredHeight());
        }
    }
}
