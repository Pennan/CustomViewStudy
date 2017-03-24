package com.np.a07customviewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a07customviewgroup.demo2.CornerLayoutActivity;
import com.np.a07customviewgroup.demo3.CornerLayoutActivity2;
import com.np.a07customviewgroup.demo4.CornerLayoutActivity3;
import com.np.a07customviewgroup.demo5.CornerLayoutActivity4;

public class MainCornerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_corner_layout);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, CornerLayoutActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, CornerLayoutActivity2.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, CornerLayoutActivity3.class);
        startActivity(intent);
    }

    public void clickDemo4(View view) {
        Intent intent = new Intent(this, CornerLayoutActivity4.class);
        startActivity(intent);
    }
}
