package com.np.a03graphics2d;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a03graphics2d.view.demo1.BallMoveActivity;
import com.np.a03graphics2d.view.demo2.CoordinateActivity;
import com.np.a03graphics2d.view.demo3.ClipActivity;
import com.np.a03graphics2d.view.demo3.ClipActivity2;
import com.np.a03graphics2d.view.watch_view.WatchActivity;

/**
 * Graphics2D 实现动态效果
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 小球循环往返滚动
    public void startMoveBallClick(View view) {
        startActivity(new Intent(this, BallMoveActivity.class));
    }

    public void coordinateChangeClick(View view) {
        startActivity(new Intent(this, CoordinateActivity.class));
    }

    public void clipClick(View view) {
        startActivity(new Intent(this, ClipActivity.class));
    }

    public void clipClick2(View view) {
        startActivity(new Intent(this, ClipActivity2.class));
    }

    public void watchClick(View view) {
        startActivity(new Intent(this, WatchActivity.class));
    }
}
