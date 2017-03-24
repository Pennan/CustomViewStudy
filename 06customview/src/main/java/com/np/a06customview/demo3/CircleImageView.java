package com.np.a06customview.demo3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.np.a06customview.R;

/**
 * package: com.np.a06customview.demo3
 * des    :
 * author : NingPan on 2017/3/17.
 */

public class CircleImageView extends ImageView {

    private int border;
    private int borderColor;
    
    private Paint paint;
    private Xfermode xfermode;
    private Path path;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        path = new Path();
        
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        border = ta.getDimensionPixelSize(R.styleable.CircleImageView_circle_border, 0);
        borderColor = ta.getColor(R.styleable.CircleImageView_circle_border_color, 0);
        ta.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
        }

        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();
        RectF ovalRect = new RectF(0, 0, viewWidth, viewHeight);

        int layer = canvas.saveLayer(getPaddingLeft(), getPaddingTop(),
                viewWidth, viewHeight, null, Canvas.ALL_SAVE_FLAG);

        Bitmap bitmap = drawable != null ? ((BitmapDrawable) drawable).getBitmap() : null;
        Rect srcRect = new Rect(0, 0,
                drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        canvas.drawBitmap(bitmap, srcRect, ovalRect, null);
        paint.setXfermode(xfermode);
        paint.setStyle(Paint.Style.FILL); // 设置实心样式
        path.reset();
        path.addOval(ovalRect, Path.Direction.CW);
        canvas.drawPath(path, paint); // Path 也能进行 位图运算
        paint.setXfermode(null);
        canvas.restoreToCount(layer);

        // 画边框
        if (border != 0) {
            paint.setStyle(Paint.Style.STROKE); // 设置空心样式
            paint.setColor(borderColor);
            paint.setStrokeWidth(border);
            // 缩小 rect
            ovalRect.inset(border / 2, border / 2);
            canvas.drawOval(ovalRect, paint);
        }
    }
}
