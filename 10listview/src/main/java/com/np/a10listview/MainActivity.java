package com.np.a10listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a10listview.demo1.SlideListItemActivity;
import com.np.a10listview.demo2.SlideListItemActivity2;
import com.np.a10listview.demo3.ExtendLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, SlideListItemActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, SlideListItemActivity2.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, ExtendLayoutActivity.class);
        startActivity(intent);
    }
}
