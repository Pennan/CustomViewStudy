package com.np.a10listview.demo3;

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
 * <br>类描述 :
 * <br>类作者 : NingPan on 2017/3/28
 */
public class SlideListItemListView3 extends ListView {

    private static final String TAG = SlideListItemListView3.class.getSimpleName();

    private static final int SNAP_VELOCITY = 600;
    private VelocityTracker mTracker;

    private int preX, firstX, firstY;
    private ExtendLayout willSlidingView; // 需要滑动的列表项 View
    // 需要滑动的列表项 View 的索引位置,初始值为 INVALID_POSITION,表示无效的索引.
    private int position = INVALID_POSITION;
    private int touchSlop; // 最小滑动距离

    /** 是否可以滑动 */
    private static final int SLIDE_MASK = 0x1;
    /** 是否回退,如果回退不能删除列表项 */
    private static final int DELETE_VISIBLE_MASK = SLIDE_MASK << 1; // 是否滑出删除按钮
    private int flag; // 标识位
    private Scroller mScroller;

    private OnRemovedItemListener onRemovedItemListener;

    public void setOnRemovedItemListener(OnRemovedItemListener onRemovedItemListener) {
        this.onRemovedItemListener = onRemovedItemListener;
    }

    public SlideListItemListView3(Context context) {
        this(context, null);
    }

    public SlideListItemListView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideListItemListView3(Context context, AttributeSet attrs, int defStyleAttr) {
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
                    willSlidingView = (ExtendLayout) this.getChildAt(currViewIndex);
                    registerDeleteViewListener(position);
                }
                restoreListItems();
                break;
            case MotionEvent.ACTION_MOVE:
                // 判断是可以滑动
                float xVelocity = mTracker.getXVelocity();
                if ((Math.abs(xVelocity) > SNAP_VELOCITY ||
                        Math.abs(currX - firstX) > touchSlop &&
                        Math.abs(currY - firstY) < touchSlop))
                    flag |= SLIDE_MASK; // 可以滑动,置 1.
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

    /** 为当前操作的 ListItem 的删除按钮注册点击删除事件 */
    private void registerDeleteViewListener(final int position) {
        willSlidingView.getDeleteView().setOnClickListener(
                new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRemovedItemListener != null) {
                    onRemovedItemListener.itemRemoved(position, (BaseAdapter) getAdapter());
                }
            }
        });
    }

    /** 恢复前面已经滑开的 ListItem */
    private void restoreListItems() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            // 防止 child 为 Header 和 Footer
            if (child instanceof ExtendLayout) {
                ExtendLayout extendLayout = (ExtendLayout) child;
                // 判断是否是正在操作的 ListItem
                if (willSlidingView != extendLayout)
                    extendLayout.scrollTo(extendLayout.getDeleteView().getWidth(), 0);
            }
        }
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
                    int deleteWidth = willSlidingView.getDeleteView().getWidth();
                    int itemScrollX = willSlidingView.getScrollX();
                    Log.i(TAG, "itemScrollX == " + itemScrollX);
                    if (currX > firstX) { // 向右滑动
                        if (Math.abs(currX - firstX) <= deleteWidth / 2) {
                            // 回滚,隐藏删除按钮
                            int remain = deleteWidth - Math.abs(itemScrollX);
                            mScroller.startScroll(itemScrollX, 0, remain, 0, Math.abs(remain));
                            flag &= ~DELETE_VISIBLE_MASK; // 隐藏删除按钮,置 0.
                        } else {
                            // 显示删除按钮
                            mScroller.startScroll(itemScrollX, 0, -itemScrollX, 0,
                                    Math.abs(itemScrollX));
                            flag |= DELETE_VISIBLE_MASK; // 显示删除按钮,置 1.
                        }
                    } else { // 向左滑动
                        if (Math.abs(currX - firstX) <= deleteWidth / 2) {
                            // 回滚,显示删除按钮
                            mScroller.startScroll(itemScrollX, 0, -itemScrollX, 0,
                                    Math.abs(itemScrollX));
                            flag |= DELETE_VISIBLE_MASK; // 显示删除按钮,置 1.
                        } else {
                            // 恢复正常 ListItem, 隐藏删除按钮
                            int remain = deleteWidth - Math.abs(itemScrollX);
                            mScroller.startScroll(itemScrollX, 0, remain, 0, Math.abs(remain));
                            flag &= ~DELETE_VISIBLE_MASK; // 隐藏删除按钮,置 0.
                        }
                    }

                    postInvalidate();
                    flag &= ~SLIDE_MASK; // 禁止滑动,置 0.
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
