package com.np.a09sidemenu.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a09sidemenu.R;

public class SliderMenuActivity2 extends AppCompatActivity {

    private SliderMenu2 menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_menu2);
        menu2 = (SliderMenu2) findViewById(R.id.slider_menu2);
    }

    public void close(View view) {
        menu2.close();
    }

    public void open(View view) {
        menu2.open();
    }
}
