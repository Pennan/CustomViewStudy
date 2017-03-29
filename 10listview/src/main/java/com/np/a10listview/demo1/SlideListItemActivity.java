package com.np.a10listview.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleAdapter;

import com.np.a10listview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideListItemActivity extends AppCompatActivity {

    private SlideListItemListView mListView;
    private List<Map<String, String>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_list_item);
        mListView = (SlideListItemListView) findViewById(R.id.listView);
        initDataList();
        mListView.setAdapter(new SimpleAdapter(this, mDataList,
                R.layout.list_item,
                new String[]{"Name", "Number"},
                new int[]{R.id.list_text1, R.id.list_text2}));
    }

    private void initDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("Name", "小圣" + i);
            map.put("Number", "N00" + i);
            mDataList.add(map);
        }
    }
}
