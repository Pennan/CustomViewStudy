package com.np.doublebuffer.demo.demo7;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * package: com.np.doublebuffer.demo.demo7
 * des    :
 * author : NingPan on 2017/3/13.
 */

public class BitmapBuffer {
    private Bitmap bitmap;
    private Canvas canvas;
    private volatile static BitmapBuffer self;

    private BitmapBuffer(int width, int height) {
        init(width, height);
    }

    private void init(int width, int height) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
    }

    public static BitmapBuffer getInstance() {
        if (self == null) {
            synchronized (BitmapBuffer.class) {
                if (self == null) {
                    self = new BitmapBuffer(SystemParams.areaWidth, SystemParams.areaHeight);
                }
            }
        }
        return self;
    }

    /** 获取绘图结果 */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /** 获取缓冲区的画布 */
    public Canvas getCanvas() {
        return canvas;
    }

    /** 将当前绘图结果保存到栈中 */
    public void pushBitmap() {
        BitmapHistory.getInstance().pushBitmap(
                bitmap.copy(Bitmap.Config.ARGB_8888, false));
    }

    /** 撤销 */
    public void redo() {
        BitmapHistory history = BitmapHistory.getInstance();
        if (history.isRedo()) {
            Bitmap bmp = history.reDo();
            if (bmp != null) {
                // 将该位图设置成可修改的
                bitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
                // 必须重新关联画布 Canvas
                canvas.setBitmap(bitmap);
                // 回收栈中的 Bitmap 对象
                if (!bmp.isRecycled()) {
                    bmp.recycle();
                    System.gc();
                    bmp = null;
                }
            } else {
                // 如果撤销栈无位图了, 则撤销到空白位图.
                bitmap = Bitmap.createBitmap(SystemParams.areaWidth,
                        SystemParams.areaHeight, Bitmap.Config.ARGB_8888);
                // 必须重新关联画布 Canvas
                canvas.setBitmap(bitmap);
            }
        }
    }

    /** 清空画板, 并清空撤销栈 */
    public void clear() {
        bitmap = Bitmap.createBitmap(SystemParams.areaWidth,
                SystemParams.areaHeight, Bitmap.Config.ARGB_8888);
        BitmapHistory.getInstance().clearStack();
    }
}
