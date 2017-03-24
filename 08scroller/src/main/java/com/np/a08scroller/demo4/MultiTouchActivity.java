package com.np.a08scroller.demo4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.np.a08scroller.R;

public class MultiTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);
        findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MultiTouchActivity.this, "美女1", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.iv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MultiTouchActivity.this, "美女2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
