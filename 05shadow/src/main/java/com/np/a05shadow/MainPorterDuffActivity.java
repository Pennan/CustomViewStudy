package com.np.a05shadow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a05shadow.demo9.demo1.PorterDuffActivity;
import com.np.a05shadow.demo9.demo2.PorterDuffActivity2;
import com.np.a05shadow.demo9.demo3.CirclePhotoActivity;
import com.np.a05shadow.demo9.demo4.CirclePhotoActivity2;
import com.np.a05shadow.demo9.demo5.AnyPhotoActivity;
import com.np.a05shadow.demo9.demo6.ScratchMusicActivity;

public class MainPorterDuffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_porter_duff);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, PorterDuffActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, PorterDuffActivity2.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, CirclePhotoActivity.class);
        startActivity(intent);
    }

    public void clickDemo4(View view) {
        Intent intent = new Intent(this, CirclePhotoActivity2.class);
        startActivity(intent);
    }

    public void clickDemo5(View view) {
        Intent intent = new Intent(this, AnyPhotoActivity.class);
        startActivity(intent);
    }

    public void clickDemo6(View view) {
        Intent intent = new Intent(this, ScratchMusicActivity.class);
        startActivity(intent);
    }
}
