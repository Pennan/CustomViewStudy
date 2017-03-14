package com.np.a03graphics2d.view.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 通过平移，我们沿 45 度方向绘制出一系列的正方形；
 * 通过缩放，我们绘制一个万花筒；通过旋转，绘制出手表四周的刻度
 */
public class CoordinateView extends View {

    public CoordinateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.save(); // 保存现场

        for (int i = 0; i < 10; i++) {
            canvas.drawRect(0, 0, 60, 60, paint);
            canvas.translate(10, 10);
        }
        canvas.restore(); // 恢复到上一个 save 之前的现场

        canvas.translate(0, 200); // 平移坐标，使接下来的图形绘制在上一次的下面
        canvas.save();

        for (int i = 0; i < 10; i++) {
            canvas.drawRect(0, 0, 200, 200, paint);
            canvas.scale(0.9f, 0.9f, 100, 100);
        }
        canvas.restore();

        canvas.translate(0, 300);
        canvas.save();

        canvas.drawCircle(150, 150, 150, paint);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(150, 0, 150, 20, paint);
            canvas.rotate(30, 150, 150); // 以圆心旋转 30 度
        }
    }
}
