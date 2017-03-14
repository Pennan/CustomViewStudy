package com.np.doublebuffer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.doublebuffer.demo.demo1.LineActivity1;
import com.np.doublebuffer.demo.demo2.LineActivity2;
import com.np.doublebuffer.demo.demo3.LineActivity3;
import com.np.doublebuffer.demo.demo4.RectActivity1;
import com.np.doublebuffer.demo.demo5.RectActivity2;
import com.np.doublebuffer.demo.demo6.RectActivity3;
import com.np.doublebuffer.demo.demo7.activity.DrawerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, LineActivity1.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, LineActivity2.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, LineActivity3.class);
        startActivity(intent);
    }

    public void clickDemo4(View view) {
        Intent intent = new Intent(this, RectActivity1.class);
        startActivity(intent);
    }

    public void clickDemo5(View view) {
        Intent intent = new Intent(this, RectActivity2.class);
        startActivity(intent);
    }

    public void clickDemo6(View view) {
        Intent intent = new Intent(this, RectActivity3.class);
        startActivity(intent);
    }

    public void clickDemo7(View view) {
        Intent intent = new Intent(this, DrawerActivity.class);
        startActivity(intent);
    }
}
