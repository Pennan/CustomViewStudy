package com.np.a06customview.demo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.np.a06customview.R;

/**
 * package: com.np.a06customview.demo2
 * des    :
 * author : NingPan on 2017/3/17.
 */

public class AttrView extends View {
    public AttrView(Context context) {
        this(context, null);
    }

    public AttrView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AttrView,
                defStyleAttr, R.style.myDefaultStyle);
        String attr1 = ta.getString(R.styleable.AttrView_attr1);
        String attr2 = ta.getString(R.styleable.AttrView_attr2);
        String attr3 = ta.getString(R.styleable.AttrView_attr3);
        String attr4 = ta.getString(R.styleable.AttrView_attr4);
        ta.recycle();
        Log.i("AttrViewTag", "attr1:" + attr1 + ",attr2:" + attr2
                + ",attr3:" + attr3 + ",attr4:" + attr4);
    }
}
