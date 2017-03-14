package com.np.a03graphics2d.view.watch_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * package: com.np.a03graphics2d.view.watch_view
 * des    :
 * author : NingPan on 2017/3/10.
 */

public class WatchView extends View {

    private int width;
    private int height;

    private static final int RADIUS = 300;

    private Paint mPaint;
    private Paint hourScalePaint;
    private Paint minuteScalePaint;
    private Paint hourPaint;
    private Paint minutePaint;
    private Paint secondPaint;

    /** 小时刻度的长度 */
    private static final int hourScaleHeight = 30;
    /** 分钟刻度的长度 */
    private static final int minuteScaleHeight = 15;

    private Calendar calendar;
    private int currentHour = 18; // 当前时间
    private int currentMinute = 20; // 当前时间
    private int currentSecond = 0; // 当前时间

    public WatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);

        hourScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourScalePaint.setColor(Color.RED);
        hourScalePaint.setStrokeWidth(5);

        minuteScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minuteScalePaint.setColor(Color.GRAY);
        minuteScalePaint.setStrokeWidth(2);

        hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.BLUE);
        hourPaint.setStrokeWidth(5);

        minutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minutePaint.setColor(Color.BLUE);
        minutePaint.setStrokeWidth(3);

        secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondPaint.setColor(Color.GRAY);
        secondPaint.setStrokeWidth(1);

        calendar = Calendar.getInstance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getTimes(); // 获取当前时间

        canvas.drawCircle(width / 2, height/ 2, RADIUS, mPaint);
        canvas.translate(width / 2, height/ 2);
        canvas.save();

        drawShortScale(canvas); // 绘制短刻度
        canvas.restore();

        canvas.save();
        drawLongScale(canvas); // 绘制长刻度
        canvas.restore();

        canvas.save();
        drawHourPoint(canvas); // 绘制时针
        canvas.restore();

        canvas.save();
        drawMinutePoint(canvas); // 绘制分针
        canvas.restore();

        canvas.save();
        drawSecondPoint(canvas); // 绘制秒针
        canvas.restore();

        canvas.save();
        drawCenterCircle(canvas); // 绘制手表中间修饰圆
        canvas.restore();

        canvas.save();
        drawHourText(canvas); // 绘制时针数字
        canvas.restore();
    }

    /** 获取当前时间 */
    private void getTimes() {
        // 先获取系统时间(否则时间不会变化)
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 获取时分秒
        currentHour = calendar.get(Calendar.HOUR_OF_DAY) % 12; // 转换成 12 小时制
        currentMinute = calendar.get(Calendar.MINUTE);
        currentSecond = calendar.get(Calendar.SECOND);
        Log.i("WatchViewTag", "hour：" + currentHour + ", minute：" + currentMinute
                + ", second：" + currentSecond);
    }

    /** 绘制秒针 */
    private void drawSecondPoint(Canvas canvas) {
        int degree = (360 / 60) * currentSecond;
        canvas.rotate(degree);
        canvas.drawLine(0, RADIUS / 4, 0, -RADIUS + hourScaleHeight + 10, secondPaint);
    }

    /** 绘制小时文本 */
    private void drawHourText(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setTextSize(50);
        for (int i = 12; i > 0; i--) {
            canvas.drawText(i + "", -25, -RADIUS + 80, paint);
            canvas.rotate(-30);
        }
    }

    /** 绘制分针 */
    private void drawMinutePoint(Canvas canvas) {
        int degree = currentMinute * (360 / 60);
        canvas.rotate(degree);
        canvas.drawLine(0, RADIUS / 6, 0, -RADIUS / 2 - 30, minutePaint);
    }

    /** 绘制手表中间修饰圆 */
    private void drawCenterCircle(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 10, paint);
    }

    /** 绘制时针 */
    private void drawHourPoint(Canvas canvas) {
        int degree = currentHour * (360 / 12);
        canvas.rotate(degree);
        canvas.drawLine(0, RADIUS / 4, 0, -RADIUS / 2, hourPaint);
    }

    /** 绘制分针刻度 */
    private void drawShortScale(Canvas canvas) {
        for (int i = 0; i < 60; i++) {
            canvas.drawLine(0, -RADIUS, 0, - RADIUS + minuteScaleHeight, minuteScalePaint);
            canvas.rotate(6);
        }
    }

    /** 绘制时针刻度 */
    private void drawLongScale(Canvas canvas) {
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(0, -RADIUS, 0, - RADIUS + hourScaleHeight, hourScalePaint);
            canvas.rotate(30);
        }
    }

    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 0, 1000);
    }
}
