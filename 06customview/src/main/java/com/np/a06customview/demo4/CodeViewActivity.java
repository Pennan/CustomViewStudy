package com.np.a06customview.demo4;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.np.a06customview.R;

public class CodeViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CodeView mCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCodeView = (CodeView) findViewById(R.id.code_view);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("验证码");
        mToolbar.inflateMenu(R.menu.menu_toobar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_refresh:
                        mCodeView.refresh();
                        break;
                    case R.id.toolbar_update_color:
                        mCodeView.setCodeTextColor(Color.RED);
                        break;
                    case R.id.toolbar_update_text:
                        mCodeView.setCodeTextFontSize(100);
                        break;
                    case R.id.toolbar_code_num:
                        mCodeView.setCodeTextNum(6);
                        break;
                    case R.id.toolbar_line_num:
                        mCodeView.setLineNum(200);
                        break;
                    case R.id.toolbar_get_code:
                        Toast.makeText(CodeViewActivity.this, "验证码：" + mCodeView.getCodeText(),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}
