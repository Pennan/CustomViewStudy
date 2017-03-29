package com.np.a09sidemenu.demo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.np.a09sidemenu.R;

/**
 * package: com.np.a09sidemenu.demo2
 * des    :
 * author : NingPan on 2017/3/27.
 */

public class SliderMenu2 extends HorizontalScrollView {

    private static final String TAG = SliderMenu2.class.getSimpleName();

    private int leftPaddingWidth; // 侧边栏的宽度
    private boolean isOpen = false; // 侧边栏是否打开
    private boolean once = false; // 默认只隐藏一次

    public SliderMenu2(Context context) {
        this(context, null);
    }

    public SliderMenu2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderMenu2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SliderMenu2);
        leftPaddingWidth = ta.getDimensionPixelSize(R.styleable.SliderMenu2_left_padding_width,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                        200, getResources().getDisplayMetrics()));
        ta.recycle();
    }

    /** 指定侧边栏和主界面的宽 */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        once = true;
        // HorizontalScrollView 只有一个子 View.
        ViewGroup subView = (ViewGroup) getChildAt(0);
        ViewGroup sliderMenu = (ViewGroup) subView.getChildAt(0);
        ViewGroup contentLayout = (ViewGroup) subView.getChildAt(1);

        sliderMenu.getLayoutParams().width = leftPaddingWidth;
        contentLayout.getLayoutParams().width = getScreenWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 下面的代码只调用一次
        if (once) {
            // 隐藏侧边栏
            this.scrollTo(leftPaddingWidth, 0);
        }
        once = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            int dx = this.getScrollX();
            int halfWidth = leftPaddingWidth / 2;
            Log.i(TAG, "dx == " + dx);
            if (dx < halfWidth) { // 显示侧边栏
                this.smoothScrollTo(0, 0);
                isOpen = true;
            } else { // 隐藏侧边栏
                this.smoothScrollTo(leftPaddingWidth, 0);
                isOpen = false;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /** 获取屏幕的宽度 */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().
                getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /** 打开侧边栏 */
    public void open() {
        if (!isOpen) {
            this.smoothScrollTo(0, 0);
            isOpen = true;
        }
    }

    /** 关闭侧边栏 */
    public void close() {
        if (isOpen) {
            this.smoothScrollTo(leftPaddingWidth, 0);
            isOpen = false;
        }
    }

    /** 打开或关闭侧边栏 */
    public void toggle() {
        if (isOpen)
            this.smoothScrollTo(leftPaddingWidth, 0);
        else
            this.smoothScrollTo(0, 0);
        isOpen = !isOpen;
    }
}
