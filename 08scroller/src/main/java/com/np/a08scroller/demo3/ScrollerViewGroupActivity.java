package com.np.a08scroller.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a08scroller.R;

public class ScrollerViewGroupActivity extends AppCompatActivity {

    private ScrollerViewGroup scrollerViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_view_group);
        scrollerViewGroup = (ScrollerViewGroup) findViewById(R.id.scroller_view_group);
    }

    public void startScroll(View view) {
        scrollerViewGroup.start();
    }

    public void stopScroll(View view) {
        scrollerViewGroup.stop();
    }

    public void goBack(View view) {
        scrollerViewGroup.goBack();
    }
}
