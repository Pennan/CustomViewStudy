package com.np.a09sidemenu.demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.np.a09sidemenu.R;

/**
 * package: com.np.a09sidemenu.demo1
 * des    :
 * author : NingPan on 2017/3/24.
 */

public class SliderMenu extends ViewGroup {

    private static final String TAG = SliderMenu.class.getSimpleName();

    private static final int DO_MOVING = 0x001; // 可以滑动
    private static final int NOT_MOVING = 0x002; // 不可以滑动
    private int moving = NOT_MOVING; // 默认为不可滑动

    /** 标记变量,是否有分割线,占用最后一位 */
    private static final int FLAG_SEPARATOR = 0x1;
    /** 标记变量,侧滑栏是否打开,占用倒数第二位 */
    private static final int FLAG_IS_OPEN = FLAG_SEPARATOR << 1;
    /** 存储标记变量 */
    private int flags = FLAG_SEPARATOR >> 1;

    private static final int SLIDER_WIDTH = 250; // 默认侧滑栏宽度
    private static final int SEPARATOR_WIDTH = 1; // 默认分割线宽度
    private static final int TOUCH_WIDTH = 50; // 默认滑动感应区宽度

    private int sliderWidth; // 侧滑栏宽度
    private int separatorWidth; // 分割线宽度
    private int touchWidth; // 滑动感应区宽度
    private int screenWidth; // 屏幕宽度

    private Scroller mScroller;
    private Paint mPaint;
    private int preX, firstX;

    public SliderMenu(Context context) {
        this(context, null);
    }

    public SliderMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SliderMenu);
        sliderWidth = ta.getDimensionPixelSize(R.styleable.SliderMenu_sliding_width,
                sp2px(SLIDER_WIDTH));
        separatorWidth = ta.getDimensionPixelSize(R.styleable.SliderMenu_separator_width,
                sp2px(SEPARATOR_WIDTH));
        touchWidth = ta.getDimensionPixelSize(R.styleable.SliderMenu_touch_width,
                sp2px(TOUCH_WIDTH));
        ta.recycle();

        if (separatorWidth > 0)
            flags = flags | FLAG_SEPARATOR;
        screenWidth = getScreenWidth(context);

        mScroller = new Scroller(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(separatorWidth);

        this.setBackgroundColor(Color.TRANSPARENT);
    }

    /** 获取屏幕宽度 */
    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.
                getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.x;
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        if (getChildCount() > 2) {
            throw new ArrayIndexOutOfBoundsException(
                    "Children count can't bt more than 2.");
        }
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
        if (mode == MeasureSpec.AT_MOST) {
            throw new IllegalStateException("layout_height can't be wrap_content.");
        } else if (mode == MeasureSpec.EXACTLY) {
            height = size;
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            throw new IllegalStateException("layout_width can't be wrap_content.");
        }
        return screenWidth + sliderWidth + separatorWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
//            if (i == 0)
//                child.layout(-sliderWidth - separatorWidth, 0,  - separatorWidth, getMeasuredHeight());
//            if (i == 1)
//                child.layout(0, 0, screenWidth, getMeasuredHeight());
            if (i == 0)
                child.layout(0, 0,  sliderWidth, getMeasuredHeight());
            if (i == 1)
                child.layout(sliderWidth + separatorWidth, 0,
                        screenWidth + sliderWidth + separatorWidth, getMeasuredHeight());
        }
        this.scrollTo(sliderWidth + separatorWidth, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画分割线
        if ((flags & FLAG_SEPARATOR) == FLAG_SEPARATOR) {
            int  left = separatorWidth / 2;
            canvas.drawLine(-left, 0, -left, getHeight(), mPaint);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 判断侧滑栏是打开的.
                if ((flags & FLAG_IS_OPEN) == FLAG_IS_OPEN) {
                        moving = DO_MOVING;
                } else {
                    // 判断触摸点是否在 滑动感应区内.
                    if (ev.getX() > touchWidth) {
                        moving = NOT_MOVING;
                    } else {
                        moving = DO_MOVING;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                moving = NOT_MOVING;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (moving == NOT_MOVING)
            return false;
        int currX = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = currX;
                firstX = currX;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = currX - preX;
                boolean isOpen = (flags & FLAG_IS_OPEN) == FLAG_IS_OPEN;
                // 在主界面时,防止手指向左滑动
//                if (!isOpen) {
//                    if (dx < 0) {
//                        moving = NOT_MOVING;
//                        return true;
//                    }
//                }
//                // 侧滑菜单打开时,防止手指向右滑动
//                if (isOpen) {
//                    if (dx > 0) {
//                        moving = NOT_MOVING;
//                        return true;
//                    }
//                }

                scrollBy(-dx, 0);

                preX = currX;
                moving = DO_MOVING;
                break;
            case MotionEvent.ACTION_UP:
                // 滑动的距离,为正表示手指向左滑动,为负表示手指向右滑动
                int distance = currX - firstX;
                int remain = sliderWidth - Math.abs(distance); // 剩余距离
                isOpen = (flags & FLAG_IS_OPEN) == FLAG_IS_OPEN;
                Log.i(TAG, "getScrollX == " + getScrollX());
                // 如果手指向右滑动,并且侧滑栏是关闭的.
                if (distance > 0 && !isOpen) {
                    if (remain > sliderWidth / 2) {
                        mScroller.startScroll(getScrollX(), 0, distance, 0);
                        flags = flags & ~FLAG_IS_OPEN;
                    } else {
                        mScroller.startScroll(getScrollX(), 0, -remain, 0);
                        flags = flags | FLAG_IS_OPEN;
                    }
                } else if (distance < 0 && isOpen) {
                    // 如果手指向左滑动,并且侧滑栏是打开的.
                    if (remain > sliderWidth / 2) {
                        mScroller.startScroll(getScrollX(), 0, distance, 0);
                        flags = flags | FLAG_IS_OPEN;
                    } else {
                        mScroller.startScroll(getScrollX(), 0, remain, 0);
                        flags = flags & ~FLAG_IS_OPEN;
                    }
                } else {
                    // 校正 (比如向左滑又向右滑)
                    mScroller.startScroll(getScrollX(), 0, distance, 0);
                }
                invalidate();
                break;
        }
        return moving == DO_MOVING;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    /** 打开侧边栏 */
    public void open() {
        boolean isOpen = (flags & FLAG_IS_OPEN) == FLAG_IS_OPEN;
        if (!isOpen) {
            mScroller.startScroll(getScrollX(), 0, -sliderWidth, 0);
            invalidate();
            flags = flags | FLAG_IS_OPEN; // 置 1
        }
    }

    /** 关闭侧边栏 */
    public void close() {
        boolean isOpen = (flags & FLAG_IS_OPEN) == FLAG_IS_OPEN;
        if (isOpen) {
            mScroller.startScroll(getScrollX(), 0, sliderWidth, 0);
            invalidate();
            flags = flags & ~FLAG_IS_OPEN; // 置 0
        }
    }

    /** 打开或关闭侧边栏 */
    public void toggle() {
        boolean isOpen = (flags & FLAG_IS_OPEN) == FLAG_IS_OPEN;
        if (isOpen)
            close();
        else
            open();
    }
}
