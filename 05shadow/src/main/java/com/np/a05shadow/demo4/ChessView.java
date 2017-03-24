package com.np.a05shadow.demo4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.np.a05shadow.demo4
 * des    :
 * author : NingPan on 2017/3/15.
 */

public class ChessView extends View {

    private static final int SIZE = 120; // 棋子的大小
    private static final int OFFSET = 10; // 发光点的偏移大小
    private Paint paint;
    private int width, height;

    public ChessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制棋盘
        drawChessBoard(canvas);
        // 绘制 4 个棋子
        drawChess(canvas, 4, 5, ChessType.BLACK);
        drawChess(canvas, 5, 5, ChessType.WHITE);
        drawChess(canvas, 5, 4, ChessType.BLACK);
        drawChess(canvas, 4, 6, ChessType.WHITE);
    }

    /**
     * 画棋子
     * @param x 列
     * @param y 行
     * @param chessType 棋子颜色
     */
    private void drawChess(Canvas canvas, int x, int y, ChessType chessType) {
        // 定义棋子颜色
        int colorOut = (chessType == ChessType.BLACK) ? Color.BLACK : Color.GRAY;
        int colorInner = Color.WHITE;
        // 定义渐变, 发光点向右下角偏移 OFFSET
        RadialGradient rg = new RadialGradient(x * SIZE + OFFSET,
                y * SIZE + OFFSET, SIZE / 2, colorInner, colorOut, Shader.TileMode.CLAMP);
        paint.setShader(rg);
        // 绘制棋子
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(10, 5, 5, Color.parseColor("#AACCCCCC")); // 添加阴影
        canvas.drawCircle(x * SIZE, y * SIZE, SIZE / 2, paint);
    }

    /** 画棋盘 */
    private void drawChessBoard(Canvas canvas) {
        paint.setColor(Color.GRAY);
        paint.setShadowLayer(0, 0, 0, Color.GRAY); // 去掉阴影
        int rows = height / SIZE; // 棋盘行数
        int cols = width / SIZE; // 棋盘列数
        for (int i = 1; i <= rows; i++) {
            canvas.drawLine(0, i * SIZE, width, i * SIZE, paint);
        }
        for (int i = 1; i <= cols; i++) {
            canvas.drawLine(i * SIZE, 0, i * SIZE, height, paint);
        }
    }

    enum ChessType {
        BLACK, WHITE;
    }
}
