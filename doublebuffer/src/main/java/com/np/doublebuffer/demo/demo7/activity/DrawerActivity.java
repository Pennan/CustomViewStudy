package com.np.doublebuffer.demo.demo7.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.np.doublebuffer.R;
import com.np.doublebuffer.demo.demo7.AttributesTool;
import com.np.doublebuffer.demo.demo7.BitmapBuffer;
import com.np.doublebuffer.demo.demo7.SystemParams;
import com.np.doublebuffer.demo.demo7.drawer.LineDrawer;
import com.np.doublebuffer.demo.demo7.drawer.OvalDrawer;
import com.np.doublebuffer.demo.demo7.drawer.RectDrawer;
import com.np.doublebuffer.demo.demo7.drawer.ShapeDrawer;
import com.np.doublebuffer.demo.demo7.view.DrawerView;

import java.lang.reflect.Method;

/** 绘图主界面 */
public class DrawerActivity extends AppCompatActivity {

    private DrawerView drawerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        initViews();
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle("绘图App");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setSubtitle("画线条");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                chooseOperation(item);
                return false;
            }
        });
    }

    private void chooseOperation(MenuItem item) {
        ShapeDrawer shapeDrawer = null;
        AttributesTool attributesTool = AttributesTool.getInstance();

        switch (item.getItemId()) {
            case R.id.toolbar_redo: // 撤销
                BitmapBuffer.getInstance().redo();
                SystemParams.isRedo = true;
                drawerView.invalidate();
                break;
            case R.id.toolbar_clear: // 清空画板
                BitmapBuffer bitmapBuffer = BitmapBuffer.getInstance();
                bitmapBuffer.clear();
                bitmapBuffer.getCanvas().setBitmap(bitmapBuffer.getBitmap());
                SystemParams.isRedo = true;
                drawerView.invalidate();
                break;
            case R.id.toolbar_line: // 画线
                shapeDrawer = new LineDrawer(drawerView);
                toolbar.setSubtitle("画线条");
                break;
            case R.id.toolbar_rect: // 画矩形
                shapeDrawer = new RectDrawer(drawerView);
                toolbar.setSubtitle("画矩形");
                break;
            case R.id.toolbar_oval: // 画椭圆
                shapeDrawer = new OvalDrawer(drawerView);
                toolbar.setSubtitle("画椭圆");
                break;
            case R.id.toolbar_circle: // 画圆
                shapeDrawer = new LineDrawer(drawerView);
                toolbar.setSubtitle("画圆");
                break;
        }
        if (shapeDrawer != null) {
            drawerView.setShapeDrawer(shapeDrawer);
        }
    }

    private void initViews() {
        drawerView = (DrawerView) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawerView.setShapeDrawer(null);
    }
}
