package com.np.a10listview.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Scroller;


/**
 *     全类名 : com.np.a10listview.demo1.SlideListItemListView
 * <br>类描述 : 向右滑动删除 ListItem 的 ListView.
 * <br>类作者 : NingPan on 2017/3/28
 */
public class SlideListItemListView2 extends ListView {

    private static final String TAG = SlideListItemListView2.class.getSimpleName();

    private static final int SNAP_VELOCITY = 600;
    private VelocityTracker mTracker;

    private int preX, firstX, firstY;
    private View willSlidingView; // 需要滑动的列表项 View
    // 需要滑动的列表项 View 的索引位置,初始值为 INVALID_POSITION,表示无效的索引.
    private int position = INVALID_POSITION;
    private int touchSlop; // 最小滑动距离

    /** 是否可以滑动 */
    private static final int SLIDE_MASK = 0x1;
    /** 是否回退,如果回退不能删除列表项 */
    private static final int IS_ROLLBACK_MASK = SLIDE_MASK << 1;
    private int flag; // 标识位
    private Scroller mScroller;

    private OnRemovedItemListener onRemovedItemListener;

    public void setOnRemovedItemListener(OnRemovedItemListener onRemovedItemListener) {
        this.onRemovedItemListener = onRemovedItemListener;
    }

    public SlideListItemListView2(Context context) {
        this(context, null);
    }

    public SlideListItemListView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideListItemListView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
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
                // 判断是否向右滑动
                float xVelocity = mTracker.getXVelocity();
                if ((currX > firstX) & (Math.abs(xVelocity) > SNAP_VELOCITY ||
                        Math.abs(currX - firstX) > touchSlop &&
                        Math.abs(currY - firstY) < touchSlop))
                    flag = flag | SLIDE_MASK; // 可以向右滑动,置 1.
                break;
            case MotionEvent.ACTION_UP:
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
        if ((flag & SLIDE_MASK) == SLIDE_MASK
                && position != INVALID_POSITION
                && willSlidingView != null) {
            int currX = (int) ev.getX();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    int dx = preX - currX;
                    willSlidingView.scrollBy(dx, 0);

                    preX = currX;
                    break;
                case MotionEvent.ACTION_UP:
                    int parentWidth = getMeasuredWidth();
                    int itemViewScrollX = willSlidingView.getScrollX();
                    Log.i(TAG, "itemViewScrollX == " + itemViewScrollX);

                    if (Math.abs(itemViewScrollX) >= parentWidth / 2) {
                        // 滑动剩余距离
                        int remain = parentWidth - Math.abs(itemViewScrollX);
                        mScroller.startScroll(itemViewScrollX, 0, -remain, 0,
                                Math.abs(remain));
                        flag &= ~IS_ROLLBACK_MASK; // 滑动删除,置 0.
                    } else {
                        mScroller.startScroll(itemViewScrollX, 0, -itemViewScrollX, 0,
                                Math.abs(itemViewScrollX));
                        flag |= IS_ROLLBACK_MASK; // 回滚,置 1.
                    }

                    postInvalidate();
                    flag &= ~SLIDE_MASK; // 禁止向右滑动,置 0.
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            willSlidingView.scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
            if (mScroller.isFinished()) {
                if (onRemovedItemListener != null) {
                    // 没有回退,则删除列表项
                    if ((flag & IS_ROLLBACK_MASK) != IS_ROLLBACK_MASK) {
                        onRemovedItemListener.itemRemoved(position,
                                (BaseAdapter) getAdapter());
                        willSlidingView.scrollTo(0, 0);
                    }
                }
            }
        }
    }

    /** 删除列表项的回调接口 */
    public interface OnRemovedItemListener {
        /**
         * 删除列表项
         * @param position 删除的列表项索引
         * @param adapter 适配器
         */
        void itemRemoved(int position, BaseAdapter adapter);
    }
}
