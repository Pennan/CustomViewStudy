package com.np.a05shadow.demo9.demo6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.np.a05shadow.R;

import java.util.Random;

/**
 * package: com.np.a05shadow.demo9.demo6
 * des    : 刮刮乐
 * author : NingPan on 2017/3/16.
 */

public class ScratchMusicView extends View {

    private Random mRandom;
    private Bitmap bitmapBuffer;
    private Canvas canvasBuffer;
    private Paint mPaint;
    private Paint clearPaint;

    private int preX, preY;
    private static final int FINGER = 85; // 涂抹的粗细

    private static final int[] BACKGROUNDS = new int[] {
            R.mipmap.meinv, R.mipmap.meinv1, R.mipmap.meinv2, R.mipmap.meinv3,
            R.mipmap.meinv4,  R.mipmap.meinv5, R.mipmap.meinv6, R.mipmap.meinv7,
            R.mipmap.meinv8, R.mipmap.meinv9, R.mipmap.meinv10, R.mipmap.meinv11
    };
    private static final String[] PRIZES = new String[] {
            "恭喜您，您中了1亿元大奖！",
            "恭喜您，您中了5000万元大奖！",
            "恭喜您，您中了1万元大奖！",
            "恭喜您，您中了100元大奖！",
            "恭喜您，您中了个安慰奖！",
            "对不起，您没有中奖！"
    };

    public ScratchMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setBackgroundPhoto();
    }

    private void init() {
        mRandom = new Random();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(sp2px(20));

        clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clearPaint.setStrokeWidth(FINGER);
        clearPaint.setStrokeCap(Paint.Cap.ROUND);
        clearPaint.setStrokeJoin(Paint.Join.ROUND);
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    /** 设置 View 的中奖背景 */
    private void setBackgroundPhoto() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getBackgroundPhoto());
        Bitmap bgBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas bgCanvas = new Canvas(bgBitmap);

        String prize = getPrize();
        Rect rect = new Rect();
        // 获取文字的长宽信息并将该信息存入 Rect 对象中
        mPaint.getTextBounds(prize, 0, prize.length(), rect);
        // 设置文字的位置(图片中间)
        int x = (bgBitmap.getWidth() - rect.width()) / 2;
        int y = (bgBitmap.getHeight() - rect.height()) / 2;
        // 设置 阴影时 需设置 View 的 layer type 为 LAYER_TYPE_SOFTWARE
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setShadowLayer(10, 0, 0, Color.RED);
        bgCanvas.drawText(prize, x, y, mPaint);
        mPaint.setShadowLayer(0, 0, 0, Color.YELLOW);
        // 为 View 设置背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(new BitmapDrawable(getResources(), bgBitmap));
        } else {
            this.setBackgroundDrawable(new BitmapDrawable(bgBitmap));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 初始化缓存区 Bitmap
        bitmapBuffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasBuffer = new Canvas(bitmapBuffer);
        // 为缓存区 Bitmap 蒙上一层灰色
        canvasBuffer.drawColor(Color.parseColor("#FF808080"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapBuffer, 0, 0, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                canvasBuffer.drawLine(preX, preY, currentX, currentY, clearPaint);
                invalidate();

                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }

    /** 获取中奖信息 */
    private String getPrize() {
        return PRIZES[mRandom.nextInt(PRIZES.length)];
    }

    /** 获取背景图片 */
    private int getBackgroundPhoto() {
        return BACKGROUNDS[mRandom.nextInt(BACKGROUNDS.length)];
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp,
                getContext().getResources().getDisplayMetrics());
    }
}
