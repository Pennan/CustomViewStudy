package com.np.a06customview.demo4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.np.a06customview.R;

import java.util.Random;

/**
 * package: com.np.a06customview.demo4
 * des    :
 * author : NingPan on 2017/3/17.
 */

public class CodeView extends View {

    private static final int CODE_COUNT = 3;
    private static final int CODE_SIZE = 28; // 单位 sp
    private static final int CODE_COLOR = Color.BLUE;
    private static final int LINE_COUNT = 100;

    /** 验证码个数 */
    private int codeCount;
    /** 验证码字体大小 */
    private int codeSize;
    /** 验证码字体颜色 */
    private int codeColor;
    /** 干扰线条数 */
    private int lineCount;
    /** 干扰线随机颜色 */
    private static final int[] lineColors = new int[] {
            Color.BLUE, Color.RED, Color.BLACK, Color.GREEN, Color.GRAY
    };

    /** 验证码文本 */
    private String codeText;
    private Paint textPaint;
    private Paint linePaint;
    private Paint outPaint;

    private Random mRandom;

    public CodeView(Context context) {
        this(context, null);
    }

    public CodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CodeView);
        codeCount = ta.getInteger(R.styleable.CodeView_code_count, CODE_COUNT);
        codeColor = ta.getColor(R.styleable.CodeView_code_text_color, CODE_COLOR);
        lineCount = ta.getInteger(R.styleable.CodeView_line_count, LINE_COUNT);
        int px = sp2px(CODE_SIZE);
        // getDimensionPixelSize() 获取的是 像素px, 而在布局文件中设置的大小的单位为 sp,
        // 然后通过 getDimensionPixelSize() 获取的就是转换后的 px.
        codeSize = ta.getDimensionPixelSize(R.styleable.CodeView_code_text_size, px);
        ta.recycle();

        mRandom = new Random();
        codeText = getCodeNum();

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(codeColor);
        textPaint.setTextSize(codeSize);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Rect rect = getTextRect();
        int width = measureWidth(widthMeasureSpec, rect.width());
        int height = measureHeight(heightMeasureSpec, rect.height());
        this.setMeasuredDimension(width, height);
    }

    private int measureHeight(int heightMeasureSpec, int rectHeight) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + rectHeight + getPaddingBottom();
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec, int rectWidth) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + rectWidth + getPaddingRight();
        }
        return width;
    }

    /** 获取文字的宽度大小 */
    private Rect getTextRect() {
        Rect rect = new Rect();
        textPaint.getTextBounds(codeText, 0, codeText.length(), rect);
        return rect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制验证码文本
        Rect textRect = getTextRect();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        int x = (width - textRect.width()) / 2;
        int y = (int) (height / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2
                - fontMetrics.descent);
        canvas.drawText(codeText, x, y, textPaint);

        // 绘制验证码干扰线
        for (int i = 0; i < lineCount; i++) {
            int color = getLineColor();
            linePaint.setColor(color);
            canvas.drawLine(mRandom.nextInt(width), mRandom.nextInt(height),
                    mRandom.nextInt(width), mRandom.nextInt(height), linePaint);
        }

        // 绘制外围边框
        Rect outRect = new Rect(0, 0, width, height);
        outRect.inset(2, 2); // 将矩形缩小一点
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setColor(codeColor);
        outPaint.setStrokeWidth(2);
        canvas.drawRect(outRect, outPaint);
    }

    /** 获取干扰线随机颜色 */
    private int getLineColor() {
        return lineColors[mRandom.nextInt(lineColors.length)];
    }

    /** 获取验证码文本内容 */
    private String getCodeNum() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeCount; i++) {
            sb.append(mRandom.nextInt(10));
        }
        return sb.toString();
    }

    /** 将单位 sp 转换成 px */
    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    private void initTextPaint() {
        textPaint.reset();
        textPaint.setColor(codeColor);
        textPaint.setTextSize(codeSize);
    }

    /** 刷新验证码 */
    public void refresh() {
        codeText = getCodeNum();
        invalidate();
    }

    /** 设置验证码字体颜色 */
    public void setCodeTextColor(int color) {
        this.codeColor = color;
        initTextPaint();
        invalidate();
    }

    /** 设置验证按字体大小 */
    public void setCodeTextFontSize(int spSize) {
        this.codeSize = sp2px(spSize);
        initTextPaint();
        requestLayout(); // 重新调整布局大小 onMeasure() ---> onDraw()
    }

    /** 设置验证码字体个数 */
    public void setCodeTextNum(int num) {
        this.codeCount = num;
        codeText = getCodeNum();
        requestLayout(); // 重新调整布局大小 onMeasure() ---> onDraw()
    }

    /** 设置验证码干扰线个数 */
    public void setLineNum(int num) {
        this.lineCount = num;
        invalidate();
    }

    /** 获取验证码文本内容 */
    public String getCodeText() {
        return codeText;
    }
}
