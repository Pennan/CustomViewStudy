package com.np.customviewstudy;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * 这是 Graphics2D API 的demo
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_main);

//        demo1();
//        demo2();
//        drawPoint();
//        drawLine();
//        drawRect();
//        drawCircle();
//        drawPathDemo();
//        drawPathDemo2();
//        drawPathDemo3();
//        arcToDemo();
//        drawPathDemo4();
        drawTextDemo();
    }

    private void drawTextDemo() {
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);

        String text = "我爱Android,刚好遇见你!";
        canvas.drawText(text, 50, 50, paint);

        canvas.drawText(text.toCharArray(), 2, 7, 50, 100, paint);

        Path path = new Path();
        path.moveTo(50, 150); // 起点
        path.quadTo(150, 100, 250, 200); // 二阶贝塞尔曲线
        canvas.drawTextOnPath(text, path, 10, -10, paint);

        // 绘制文字时不能设置 style 为 STROKE,这样会使字体表模糊
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        mImageView.setImageBitmap(bitmap);
    }

    @TargetApi(19)
    private void drawPathDemo4() {
        Bitmap bitmap = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.FILL); // 可修改成 STROKE
        Path path1 = new Path();
        path1.addRect(new RectF(10, 10, 110, 110), Path.Direction.CCW);

        Path path2 = new Path();
        path2.addCircle(100, 100, 50, Path.Direction.CCW);

        path1.op(path2, Path.Op.XOR); // 可修改其他组合效果

        canvas.drawPath(path1, paint);
//        paint.setColor(Color.RED);
//        canvas.drawPath(path2, paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void arcToDemo() {
        Bitmap bitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 100);
        path.arcTo(new RectF(100, 150, 300, 300), -15, 60, false);
        canvas.drawPath(path, paint);

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        canvas.drawOval(new RectF(100, 150, 300, 300), paint);

        Path path1 = new Path();
        path1.moveTo(100, 100);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        path1.arcTo(new RectF(100, 320, 300, 470), -15, 60, true);
        canvas.drawPath(path1, paint);

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        canvas.drawOval(new RectF(100, 320, 300, 470), paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void drawPathDemo3() {
        Bitmap bitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 100);
        path.quadTo(200, 10, 300, 300);

        canvas.drawPath(path, paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawPoints(new float[]{100, 100, 200, 10, 300, 300}, paint);

        mImageView.setImageBitmap(bitmap);
    }

    @TargetApi(21)
    private void drawPathDemo2() {
        Bitmap bitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.addRect(0, 0, 200, 100, Path.Direction.CW); // 矩形
        // 圆角矩形：可以绘制 四个不同的弧线弧度
        path.addRoundRect(0, 110, 200, 210,
                new float[] {5, 6, 10, 10, 20, 20, 30, 30}, Path.Direction.CCW);
        path.addOval(0, 220, 200, 320, Path.Direction.CW); // 椭圆
        path.addCircle(100, 400, 50, Path.Direction.CW); // 圆
        path.addArc(0, 520, 200, 620, -15, -60); // 弧线

        canvas.drawPath(path, paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void drawPathDemo() {
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(0, 150);
        path.rLineTo(300, 0);
        path.rLineTo(-300, 150);
        path.rLineTo(150, -300);
        path.rLineTo(150, 300);
        path.close();

        canvas.drawPath(path, paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void drawCircle() {
        Bitmap bitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF1 = new RectF(0, 0, 400, 200);
        canvas.drawOval(rectF1, paint);

        paint.setColor(Color.RED);
        canvas.drawArc(rectF1, -30, -30, false, paint);

        paint.setColor(Color.GRAY);
        RectF rectF2 = new RectF(0, 210, 400, 410);
        canvas.drawOval(rectF2, paint);

        paint.setColor(Color.RED);
        canvas.drawArc(rectF2, -30, -30, true, paint);

        mImageView.setImageBitmap(bitmap);
    }

    @TargetApi(21)
    private void drawRect() {
        Bitmap bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        RectF rect = new RectF(0, 0, 400, 300);
        canvas.drawRoundRect(rect, 6, 8, paint);

        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRoundRect(0, 320, 400, 620, 18, 16, paint);
        mImageView.setImageBitmap(bitmap);
    }

    private void drawLine() {
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        canvas.drawLine(50, 50, 50, 150, paint);

        paint.setColor(Color.RED);
        float[] pts = {100, 50, 100, 150, 150, 50, 150, 150};
        canvas.drawLines(pts, paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void drawPoint() {
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);

        canvas.drawPoint(100, 100, paint);

        paint.setColor(Color.BLUE);
        float[] pts = {100, 200, 200, 300, 200, 150};
        canvas.drawPoints(pts, paint);

        paint.setColor(Color.GREEN);
        canvas.drawPoints(pts, 1, 4, paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void demo2() {
        Bitmap bitmap = Bitmap.createBitmap(400, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 原图大小绘制
        canvas.drawBitmap(bmp, 0, 0, null);
        // 对位图放大 2 倍
        Rect rect1 = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        Rect rect2 = new Rect(0, bmp.getHeight(), bmp.getWidth() * 2,
                bmp.getHeight() * 2 + bmp.getHeight());
        canvas.drawBitmap(bmp, rect1, rect2, null);

        mImageView.setImageBitmap(bitmap);
    }

    private void demo1() {
        Bitmap bitmap = Bitmap.createBitmap(400, 420, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setTextSize(25);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setFakeBoldText(true);
        paint.setUnderlineText(true);
        paint.setStrikeThruText(true);
        canvas.drawText("你从来都不知道", 20, 20, paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(20);
        canvas.drawRect(new Rect(100, 100, 200, 200), paint);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(100, 220, 200, 320), paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0);
        canvas.drawRect(new Rect(100, 100, 200, 200), paint);

        mImageView.setImageBitmap(bitmap);
    }
}
