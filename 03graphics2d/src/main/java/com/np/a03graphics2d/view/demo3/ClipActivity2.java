package com.np.a03graphics2d.view.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.np.a03graphics2d.R;

import java.util.Timer;
import java.util.TimerTask;

public class ClipActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip2);

        startClipView();
    }

    private void startClipView() {
        final ClipView2 clipView = (ClipView2) findViewById(R.id.clip_view2);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                clipView.postInvalidate();
            }
        }, 200, 100);
    }
}
