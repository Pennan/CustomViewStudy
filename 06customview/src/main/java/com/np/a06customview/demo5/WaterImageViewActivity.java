package com.np.a06customview.demo5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.np.a06customview.R;

public class WaterImageViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WaterImageView mWaterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_image_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mWaterImageView = (WaterImageView) findViewById(R.id.water_image_view);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("验证码");
        mToolbar.inflateMenu(R.menu.menu_toobar2);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_left_top:
                        mWaterImageView.setWaterPosition(0);
                        break;
                    case R.id.toolbar_left_bottom:
                        mWaterImageView.setWaterPosition(2);
                        break;
                    case R.id.toolbar_right_top:
                        mWaterImageView.setWaterPosition(1);
                        break;
                    case R.id.toolbar_right_bottom:
                        mWaterImageView.setWaterPosition(3);
                        break;
                }
                return false;
            }
        });
    }
}
