package com.np.a06customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a06customview.demo1.FirstActivity;
import com.np.a06customview.demo2.AttrActivity;
import com.np.a06customview.demo3.CircleImageViewActivity;
import com.np.a06customview.demo4.CodeViewActivity;
import com.np.a06customview.demo5.WaterImageViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, AttrActivity.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, CircleImageViewActivity.class);
        startActivity(intent);
    }

    public void clickDemo4(View view) {
        Intent intent = new Intent(this, CodeViewActivity.class);
        startActivity(intent);
    }

    public void clickDemo5(View view) {
        Intent intent = new Intent(this, WaterImageViewActivity.class);
        startActivity(intent);
    }
}
