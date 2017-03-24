package com.np.a08scroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.np.a08scroller.demo1.ScrollToByActivity;
import com.np.a08scroller.demo2.ScrollToByActivity2;
import com.np.a08scroller.demo3.ScrollerViewGroupActivity;
import com.np.a08scroller.demo4.MultiTouchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = new TextView(this);
        view.scrollBy(0, 0);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, ScrollToByActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, ScrollToByActivity2.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, ScrollerViewGroupActivity.class);
        startActivity(intent);
    }

    public void clickDemo4(View view) {
        Intent intent = new Intent(this, MultiTouchActivity.class);
        startActivity(intent);
    }
}
