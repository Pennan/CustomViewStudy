package com.np.a03graphics2d.view.demo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.np.a03graphics2d.R;


public class ClipView extends View {

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.meinv);
        // 绘制完整的美女照片
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 平移坐标
        canvas.translate(0, 500);
        // 定义剪切区
        canvas.clipRect(100, 0, 200, 200);

        // 定义一个新的 剪切区, 与上一个剪切区做 Op 运算
        // 注意这里的 Op 类是属于 Region 类下的, 而不是 Path 类下的.
        Path path = new Path();
        path.addCircle(200, 250, 100, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.UNION);

        // 再次绘制美女照片
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
