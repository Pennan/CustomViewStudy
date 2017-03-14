package com.np.a03graphics2d.view.watch_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.np.a03graphics2d.R;

public class WatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        WatchView watchView = (WatchView) findViewById(R.id.watchView);
        watchView.run();
    }
}
