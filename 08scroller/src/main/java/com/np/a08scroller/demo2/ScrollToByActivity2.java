package com.np.a08scroller.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.np.a08scroller.R;

public class ScrollToByActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_to_by2);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        findViewById(R.id.scrollTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int scrollX = layout.getScrollX();
                int scrollY = layout.getScrollY();
                Log.i("ScrollTo", "scrollX = " + scrollX + ", scrollY = " + scrollY);
                layout.scrollTo(scrollX -sp2px(5), scrollY);
            }
        });
        findViewById(R.id.scrollBy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-sp2px(5), 0);
            }
        });
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }
}
