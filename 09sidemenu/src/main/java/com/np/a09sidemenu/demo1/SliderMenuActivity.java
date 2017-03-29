package com.np.a09sidemenu.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.np.a09sidemenu.R;

public class SliderMenuActivity extends AppCompatActivity {

    private SliderMenu mSliderMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_menu);
        mSliderMenu = (SliderMenu) findViewById(R.id.slider_menu);
    }

    public void closeSlider(View view) {
        mSliderMenu.close();
    }

    public void openSlider(View view) {
        mSliderMenu.open();
    }
}
