package com.np.a03graphics2d.view.demo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.np.a03graphics2d.R;

/**
 * 全景图播放
 */
public class ClipView2 extends View {
    private int i = 0;
    private Bitmap mBitmap;
    private boolean direction; // 播放的方向

    public ClipView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_clip);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int bmpWidth = mBitmap.getWidth(); // 播放位图的宽度
        int bmpHeight = mBitmap.getHeight(); // 播放位图的高度

        int frameWidth = bmpWidth / 100; // 每次加载的图片区域
        int viewWidth = getMeasuredWidth(); // 当前 View 的宽度

        Rect rect = new Rect(0, 0, viewWidth, bmpHeight);
        canvas.clipRect(rect); // 剪切整个View作为播放区域

        canvas.drawBitmap(mBitmap, -i * frameWidth, 0, null);

        if (i * frameWidth + viewWidth >= bmpWidth) { // 正播放完整个图片时逆播放
            direction = false;
        }
        if (i == 0) { // 你播放完整个图片时正播放
            direction = true;
        }
        i = direction ? i + 1 : i - 1;
    }
}
