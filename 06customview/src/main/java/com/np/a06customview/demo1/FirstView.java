package com.np.a06customview.demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.np.a06customview.R;

/**
 * package: com.np.a06customview.demo1
 * des    :
 * author : NingPan on 2017/3/16.
 */

public class FirstView extends View {

    private String text = "Android自定义组件，牛！";
    private Paint paint;

    public FirstView(Context context) {
        this(context, null);
    }

    public FirstView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.FirstView);
        text = ta.getString(R.styleable.FirstView_text);
        ta.recycle(); // 系统资源需手动回收
    }

    public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(60);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Rect rect = getTextRect();
        int textWidth = rect.width();
        int textHeight = rect.height();
        int width = measureWidth(widthMeasureSpec, textWidth);
        int height = measureHeight(heightMeasureSpec, textHeight);
        setMeasuredDimension(width, height);
    }

    /** 获取文字所占的尺寸 */
    private Rect getTextRect() {
        // 根据 Paint 设置的绘制参数计算文字所占的宽度
        Rect rect = new Rect();
        // 将文字所占的区域大小保存到 rect 对象中.
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    private int measureHeight(int heightMeasureSpec, int textHeight) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                // 高度为 match_parent 或 具体指时，高度即为 size
                height = size;
                break;
            case MeasureSpec.AT_MOST:
                // 高度为 wrap_content，高度需要计算
                height = textHeight;
                break;
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec, int textWidth) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                // 宽度为 match_parent 或 具体指时，宽度即为 size
                width = size;
                break;
            case MeasureSpec.AT_MOST:
                // 宽度为 wrap_content，宽度需要计算
                width = textWidth;
                break;
        }
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = this.getTextRect();
        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();

        // 将文字绘制在组件中间
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int x = (viewWidth - rect.width()) / 2;
        int y = (int) (viewHeight / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2
                - fontMetrics.descent);
        canvas.drawText(text, x, y, paint);
    }
}
