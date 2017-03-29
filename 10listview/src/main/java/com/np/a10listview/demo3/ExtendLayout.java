package com.np.a10listview.demo3;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * package: com.np.a10listview.demo3
 * des    :
 * author : NingPan on 2017/3/28.
 */

public class ExtendLayout extends ViewGroup {

    private static final String TAG = ExtendLayout.class.getSimpleName();

    public ExtendLayout(Context context) {
        this(context, null);
    }

    public ExtendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 将删除按钮添加到 ExtendLayout 的第一个元素位置.
        this.addView(createDeleteView(), 0);
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        // 除了添加的删除按钮外,ExtendLayout 只能有一个子 View.
        if (getChildCount() > 2)
            throw new IndexOutOfBoundsException("ExtendLayout must only one child.");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST ||
                mode == MeasureSpec.UNSPECIFIED) {
            for (int i = 0; i < getChildCount(); i++) {
                if (i == 1)
                    height = getChildAt(i).getMeasuredHeight();
            }
        }

        // 重新调整 删除按钮 的高度,使其与 ExtendLayout 同高.
        View deleteView = getChildAt(0);
        ViewGroup.LayoutParams params = new LayoutParams(
                deleteView.getMeasuredWidth(), height);
        deleteView.setLayoutParams(params);

        Log.i(TAG, "height == " + height + ", deleteHeight == "
                + deleteView.getMeasuredHeight());
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST ||
                mode == MeasureSpec.UNSPECIFIED) {
            for (int i = 0; i < getChildCount(); i++) {
                if (i == 1)
                    width = getChildAt(i).getMeasuredWidth();
            }
        }
        // ExtendLayout 的宽度为 删除按钮宽度 加上 第二个子 View 的宽度.
        return width + getChildAt(0).getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int deleteViewWidth = getChildAt(0).getMeasuredWidth();
        for (int i = 0; i < getChildCount(); i++) {
            if (i == 0)
                getChildAt(i).layout(0, 0, deleteViewWidth, getMeasuredHeight());
            if (i == 1)
                getChildAt(i).layout(deleteViewWidth, 0,
                        deleteViewWidth + getChildAt(i).getMeasuredWidth(),
                        getMeasuredHeight());
        }
        // 隐藏删除按钮
        this.scrollTo(deleteViewWidth, 0);
    }

    /** 创建删除 View */
    private View createDeleteView() {
        TextView view = new TextView(getContext());
        view.setText("删除");
        view.setBackgroundColor(Color.RED);
        view.setTextColor(Color.WHITE);
        view.setGravity(Gravity.CENTER);

        final float scale = getContext().getResources().getDisplayMetrics().density;
        int px = (int) (10 * scale + 0.5f);
        view.setPadding(px * 2, px, px * 2, px);

        ViewGroup.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        view.setClickable(true);
        return view;
    }

    /** 获取删除按钮 */
    public View getDeleteView() {
        return getChildAt(0);
    }
}
