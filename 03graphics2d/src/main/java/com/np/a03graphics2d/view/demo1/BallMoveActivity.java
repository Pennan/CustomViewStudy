package com.np.a03graphics2d.view.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.np.a03graphics2d.R;

import java.util.Timer;
import java.util.TimerTask;

public class BallMoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_move);

        startMoveBall();
    }

    private void startMoveBall() {
        final BallMoveView ballMoveView = (BallMoveView) findViewById(R.id.ball_move_view);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ballMoveView.postInvalidate();
            }
        }, 200, 50); // 延迟 200 ms 开始计时, 每隔 50 ms 执行一次
    }
}
