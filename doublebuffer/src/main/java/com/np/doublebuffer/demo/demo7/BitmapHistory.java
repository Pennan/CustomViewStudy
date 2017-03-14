package com.np.doublebuffer.demo.demo7;

import android.graphics.Bitmap;

import java.util.Stack;

/**
 * package: com.np.doublebuffer.demo.demo7
 * des    :
 * author : NingPan on 2017/3/13.
 */

public class BitmapHistory {
    private static Stack<Bitmap> stack;
    private volatile static BitmapHistory self;

    private BitmapHistory() {
        if (stack == null) {
            stack = new Stack<>();
        }
    }

    public static BitmapHistory getInstance() {
        if (self == null) {
            synchronized (BitmapHistory.class) {
                if (self == null) {
                    self = new BitmapHistory();
                }
            }
        }
        return self;
    }

    /** 将当前的历史绘图结果压栈 */
    public void pushBitmap(Bitmap bitmap) {
        int count = stack.size();
        if (count >= 50) {
            Bitmap bmp = stack.remove(0);
            if (!bmp.isRecycled()) {
                bmp.recycle();
                System.gc();
                bmp = null;
            }
        }
        stack.push(bitmap);
    }

    /** 撤销：返回撤销后的位图,即当前位于栈顶的位图
     * Stack: pop() 和 peek() 方法的区别
     * pop(): 返回栈顶元素并删除栈顶元素
     * peek(): 仅返回栈顶元素, 不删除栈顶的元素。
     */
    public Bitmap reDo() {
        Bitmap bitmap = stack.pop(); // 弹出栈顶位图
        // 回收位图资源
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            System.gc();
            bitmap = null;
        }
        if (stack.empty()) return null;
        // 返回撤销后位于栈顶的位图对象
        return stack.peek();
    }

    /** 判断是否还能撤销: true-能撤销, false-不能撤销 */
    public boolean isRedo() {
        return !stack.empty();
    }

    /** 清空撤销栈 */
    public void clearStack() {
        if (!stack.empty()) {
            int count = stack.size();
            for (int i = 0; i < count; i++) {
                Bitmap bitmap = stack.pop();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    System.gc();
                    bitmap = null;
                }
            }
        }
    }
}
