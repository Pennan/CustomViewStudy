package com.np.doublebuffer.demo.demo7;

import android.graphics.Color;
import android.graphics.Paint;

/** 用于存储绘制参数，并将绘图参数转换成 Paint 对象 */
public class AttributesTool {
    /** 绘图颜色 */
    private int color;
    /** 线条的宽度 */
    private int borderWidth;
    /** 是否填充, 默认为空心 */
    private boolean fill;
    private volatile static AttributesTool self;
    private static Paint paint;

    private AttributesTool() {
        reset();
    }

    public static AttributesTool getInstance() {
        if (self == null) {
            synchronized (AttributesTool.class) {
                if (self == null) {
                    self = new AttributesTool();
                }
            }
        }
        return self;
    }

    /** 将绘图参数转换成 Paint 对象 */
    public Paint getPaint() {
        if (paint == null) {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        paint.setColor(color);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
        paint.setTextSize(5);

        return paint;
    }

    /** 重置 */
    private void reset() {
        this.color = Color.BLUE;
        this.borderWidth = 1;
        this.fill = false;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
