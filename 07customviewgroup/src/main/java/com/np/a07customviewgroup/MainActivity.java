package com.np.a07customviewgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.np.a07customviewgroup.demo1.SizeViewGroupActivity;
import com.np.a07customviewgroup.demo6.FlowLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDemo1(View view) {
        Intent intent = new Intent(this, SizeViewGroupActivity.class);
        startActivity(intent);
    }

    public void clickDemo2(View view) {
        Intent intent = new Intent(this, MainCornerLayoutActivity.class);
        startActivity(intent);
    }

    public void clickDemo3(View view) {
        Intent intent = new Intent(this, FlowLayoutActivity.class);
        startActivity(intent);
    }
}
