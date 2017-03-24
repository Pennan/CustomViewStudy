package com.np.a08scroller.demo3;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Scroller;

import com.np.a08scroller.R;

/**
 * package: com.np.a08scroller.demo3
 * des    :
 * author : NingPan on 2017/3/23.
 */

public class ScrollerViewGroup extends ViewGroup {

    private Scroller mScroller;

    public ScrollerViewGroup(Context context) {
        this(context, null);
    }

    public ScrollerViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);

        ViewGroup.LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        Button button = new Button(context);
        button.setText("Scroller的基本使用");
        button.setAllCaps(false);
        this.addView(button, params);
        this.setBackgroundColor(ContextCompat.getColor(context,
                R.color.colorAccent));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST
                || MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("Must be MeasureSpec.EXACTLY.");
        }
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 该 demo 只是为了演示 Scroller 类的基本使用
        View child = getChildAt(0);
        child.layout(50, 50, child.getMeasuredWidth() + 50,
                child.getMeasuredHeight() + 50);
    }

    /** 重写 View 的该方法,实现平滑滚动. */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            // 设置容器内组件的新位置
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 重绘以刷新产生动画
            postInvalidate();
        }
    }

    /** 从当前位置开始滚动,x 轴方向向右滚动 900像素,y 方向不变 */
    public void start() {
        mScroller.startScroll(this.getScrollX(), this.getScrollY(), -900, 0, 5000);
        postInvalidate();
    }

    /** 取消滚动,停止在当前位置 */
    public void stop() {
        mScroller.abortAnimation();
    }

    public void goBack() {
        this.scrollTo(0, 0);
    }
}
