package com.np.a05shadow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a05shadow.demo1.ShaderActivity;
import com.np.a05shadow.demo2.LinearGradientActivity;
import com.np.a05shadow.demo3.RadialGradientActivity;
import com.np.a05shadow.demo4.ChessActivity;
import com.np.a05shadow.demo5.SweepGradientActivity;
import com.np.a05shadow.demo6.BitmapShaderActivity;
import com.np.a05shadow.demo7.ComposeShaderActivity;
import com.np.a05shadow.demo8.GradientMatrixActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, ShaderActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, LinearGradientActivity.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, RadialGradientActivity.class);
        startActivity(intent);
    }

    public void clickDemo4(View view) {
        Intent intent = new Intent(this, ChessActivity.class);
        startActivity(intent);
    }

    public void clickDemo5(View view) {
        Intent intent = new Intent(this, SweepGradientActivity.class);
        startActivity(intent);
    }

    public void clickDemo6(View view) {
        Intent intent = new Intent(this, BitmapShaderActivity.class);
        startActivity(intent);
    }

    public void clickDemo7(View view) {
        Intent intent = new Intent(this, ComposeShaderActivity.class);
        startActivity(intent);
    }

    public void clickDemo8(View view) {
        Intent intent = new Intent(this, GradientMatrixActivity.class);
        startActivity(intent);
    }

    public void clickDemo9(View view) {
        Intent intent = new Intent(this, MainPorterDuffActivity.class);
        startActivity(intent);
    }
}
