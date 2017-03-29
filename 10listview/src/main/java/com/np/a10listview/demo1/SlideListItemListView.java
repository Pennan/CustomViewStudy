package com.np.a10listview.demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;


/**
 *     全类名 : com.np.a10listview.demo1.SlideListItemListView
 * <br>类描述 : ListItem 可以左右滑动的 ListView
 * <br>类作者 : NingPan on 2017/3/28
 */
public class SlideListItemListView extends ListView {

    private static final int SNAP_VELOCITY = 600;
    private VelocityTracker mTracker;

    private int preX, firstX, firstY;
    private View willSlidingView; // 需要滑动的列表项 View
    private boolean isLeftRightSlide; // item 是否可以左右滑动
    // 需要滑动的列表项 View 的索引位置,初始值为 INVALID_POSITION,表示无效的索引.
    private int position = INVALID_POSITION;
    private int touchSlop; // 最小滑动距离

    public SlideListItemListView(Context context) {
        this(context, null);
    }

    public SlideListItemListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideListItemListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 为 VelocityTracker 创建和关联事件
        if (mTracker == null) {
            mTracker = VelocityTracker.obtain();
        }
        mTracker.addMovement(ev);

        int currX = (int) ev.getX();
        int currY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = preX =  currX;
                firstY = currY;

                // 获取到当前触摸的 ItemView
                position = this.pointToPosition(firstX, firstY);
                if (position != INVALID_POSITION) {
                    int firstVisibleViewIndex = this.getFirstVisiblePosition();
                    int currViewIndex = position - firstVisibleViewIndex;
                    willSlidingView = this.getChildAt(currViewIndex);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 判断是否为左右滑动
                float xVelocity = mTracker.getXVelocity();
                if (Math.abs(xVelocity) > SNAP_VELOCITY ||
                        Math.abs(currX - firstX) > touchSlop &&
                        Math.abs(currY - firstY) < touchSlop)
                    isLeftRightSlide = true;
                break;
            case MotionEvent.ACTION_UP:
                isLeftRightSlide = false;

                // 释放并回收 VelocityTracker 资源
                if (mTracker != null) {
                    mTracker.clear();
                    mTracker.recycle();
                    mTracker = null;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isLeftRightSlide && position != INVALID_POSITION) {
            int currX = (int) ev.getX();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 不会执行 onTouchEvent 的 ACTION_DOWN 事件,因为在 dispatchTouchEvent 的
                    // ACTION_MOVE 事件里才使 isLeftRightSlide = true.而应该在
                    // dispatchTouchEvent 的 ACTION_DOWN 就给 preX 赋值.
                    // preX = currX;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dx = preX - currX;
                    willSlidingView.scrollBy(dx, 0);

                    preX = currX;
                    break;
                case MotionEvent.ACTION_UP:
                    // 初始化数据
                    willSlidingView = null;
                    position = INVALID_POSITION;
                    isLeftRightSlide = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }
}
