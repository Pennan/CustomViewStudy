package com.np.a08scroller.demo4;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * package: com.np.a08scroller.demo4
 * des    :
 * author : NingPan on 2017/3/23.
 */

public class MultiTouchLayout extends ViewGroup {

    private static final String TAG = MultiTouchLayout.class.getSimpleName();

    private int mTouchSlop = 0; // 最小滑动距离,超过了,才认为是滑动.

    private Scroller mScroller;

    public MultiTouchLayout(Context context) {
        this(context, null);
    }

    public MultiTouchLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
    private int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("Must not be " +
                    "MeasureSpec.AT_MOST.");
        } else if (mode == MeasureSpec.EXACTLY) {
            height = size;
        }
        return height;
    }
    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("Must not be " +
                    "MeasureSpec.AT_MOST.");
        } else if (mode == MeasureSpec.EXACTLY) {
            width = size;
        }
        // 容器的宽度时屏幕的 n 倍,n 是容器中子组件的个数
        Log.i(TAG, "onMeasure width = " + width * getChildCount());
        return width * getChildCount();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 子组件的宽度(最好使用下面那种方法)
        // int viewWidth = getMeasuredWidth() / childCount;
        int viewWidth = (r - l) / childCount;
        // 子组件的高度
        int viewHeight = getMeasuredHeight();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(i * viewWidth, 0, (i + 1) * viewWidth, viewHeight);
        }
    }

    private static final int TOUCH_STATE_STOP = 0x001; // 停止状态
    private static final int TOUCH_STATE_FLING = 0x002; // 滑动状态

    private int mTouchState = TOUCH_STATE_STOP;
    private int mPreMotionX; // 上次触摸屏的 x 位置

    /** 容器根据状态决定是否拦截事件,滚动的时候拦截事件 */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPreMotionX = (int) ev.getX();
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_STOP :
                        TOUCH_STATE_FLING;
                break;
            case MotionEvent.ACTION_MOVE:
                // 判断是否滑动
                int currX = (int) ev.getX();
                int dx = Math.abs(currX - mPreMotionX);
                if (dx > mTouchSlop) {
                    mTouchState = TOUCH_STATE_FLING;
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouchState = TOUCH_STATE_STOP;
                break;
        }
        return mTouchState != TOUCH_STATE_STOP;
    }

    private int currScreen; // 当前所在屏

    /** 滚动到第 toScreen 屏, toScreen 的范围为 [0, n- 1] */
    public void moveToScreen(int toScreen) {
        Log.i(TAG, "moveToScreen");
        currScreen = toScreen;
        Log.i(TAG, "currScreen = " + currScreen);
        // 控制滚动的边界
        if (currScreen > getChildCount() - 1)
            currScreen = getChildCount() - 1;
        if (currScreen < 0) currScreen = 0;

        // 单屏的宽度
        int screenWidth = getWidth() / getChildCount();
        int scrollX = getScrollX(); // 滚动的距离
        int dx = currScreen * screenWidth - scrollX; // 需要滚动的距离
        Log.i(TAG, "dx = " + dx);
        // 开始移动
        mScroller.startScroll(scrollX, 0, dx, 0, Math.abs(dx));
        invalidate();
    }

    /** 滚动到目标屏 */
    public void moveToDestination() {
        // 每一屏的宽度
        int screenWidth = getWidth() / getChildCount();
        // 判断是回滚还是滚入下一屏
        int toScreen = (getScrollX() + screenWidth / 2) / screenWidth;
        // 移动到目标分屏
        moveToScreen(toScreen);
    }

    /** 移动到下一屏 */
    public void moveToNext() {
        moveToScreen(currScreen + 1);
    }

    /** 移动到上一屏 */
    public void moveToPrevious() {
        moveToScreen(currScreen - 1);
    }

    private VelocityTracker mTracker;
    private static final float SNAP_VELOCITY = 1000f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTracker == null) {
            mTracker = VelocityTracker.obtain();
        }
        mTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果手指按下时,如果正在滚动,则立刻停止.
                if (mScroller != null && !mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mPreMotionX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int currX = (int) event.getX();
                int dx = mPreMotionX - currX;
                Log.i(TAG, "onTouchEvent dx = " + dx);
                scrollBy(dx, 0);

                mPreMotionX = currX;
                break;
            case MotionEvent.ACTION_UP:
                // 设置 maxVelocity 值为 0.01,速率大于 0.01 时,显示的速率为 0.01;
                // 速率小于 0.01 时,显示正常.
                // mTracker.computeCurrentVelocity(1, SNAP_VELOCITY);

                mTracker.computeCurrentVelocity(1000);
                int xVelocity = (int) mTracker.getXVelocity();
                Log.i(TAG, "xVelocity = " + xVelocity);
                // 通过 xVelocity 的正负值判断滑动方向
                if (xVelocity > SNAP_VELOCITY && currScreen > 0) {
                    moveToPrevious();
                } else if (xVelocity < -SNAP_VELOCITY && currScreen < getChildCount() - 1) {
                    moveToNext();
                } else {
                    moveToDestination();
                }

                if (mTracker != null) {
                    mTracker.clear();
                    mTracker.recycle();
                    mTracker = null;
                }
                mTouchState = TOUCH_STATE_STOP;
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
