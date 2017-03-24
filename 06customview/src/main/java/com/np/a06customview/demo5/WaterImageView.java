package com.np.a06customview.demo5;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.np.a06customview.R;


/**
 * package: com.np.a06customview.demo5
 * des    :
 * author : NingPan on 2017/3/20.
 */

public class WaterImageView extends ImageView {

    private static final String WATER_TEXT = "hello world!";

    /** 水印图片 */
    private int waterImage;
    /** 水印文字 */
    private String waterText;
    /** 水印图片位置 */
    private int waterPosition;

    private Paint mPaint;

    public WaterImageView(Context context) {
        this(context, null);
    }

    public WaterImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WaterImageView);
        waterImage = ta.getResourceId(R.styleable.WaterImageView_water_image,
                R.mipmap.ic_launcher);
        waterText = ta.getString(R.styleable.WaterImageView_water_text);
        // 获取 枚举类型的 自定义属性
        if (ta.hasValue(R.styleable.WaterImageView_water_position)) {
            waterPosition = ta.getInt(R.styleable.WaterImageView_water_position, 0);
        }
        ta.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(sp2px(14));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

//        Drawable drawable = ContextCompat.getDrawable(getContext(), waterImage);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), waterImage);
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        int left = 0;
        int top = 0;

        Rect textRect = getTextRect();
        int x = 0;
        int y = imageHeight + textRect.height();
        switch (waterPosition) {
            case 0: // 左上角
                x = left = 0;
                top = 0;
                y = imageHeight + textRect.height();
                break;
            case 1: // 右上角
                x = left = width - Math.max(imageWidth, textRect.width());
                top = 0;
                y = imageHeight + textRect.height();
                break;
            case 2: // 左下角
                x = left = 0;
                top = height - imageHeight - textRect.height();
                y = height - textRect.bottom;
                break;
            case 3: // 右下角
                x = left = width - Math.max(imageWidth, textRect.width());
                top = height - imageHeight - textRect.height();
                y = height - textRect.bottom;
                break;
        }
        canvas.drawBitmap(bitmap, left, top, null);
        canvas.drawText(waterText, x, y, mPaint);
    }

    private Rect getTextRect() {
        Rect rect = new Rect();
        waterText = null == waterText ? WATER_TEXT : waterText;
        mPaint.getTextBounds(waterText, 0, waterText.length(), rect);
        return rect;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    /** 设置水印图片位置 */
    public void setWaterPosition(int position) {
        this.waterPosition = position;
        invalidate();
    }
}
