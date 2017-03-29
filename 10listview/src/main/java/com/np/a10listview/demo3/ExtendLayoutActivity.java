package com.np.a10listview.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import com.np.a10listview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendLayoutActivity extends AppCompatActivity {
    private List<Map<String, String>> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_layout);
        SlideListItemListView3 mListView = (SlideListItemListView3)
                findViewById(R.id.listView3);
        initDataList();
        mListView.setAdapter(new SimpleAdapter(this, mDataList,
                R.layout.list_item3, new String[]{"Name", "Number"},
                new int[]{R.id.item_text1, R.id.item_text2}));
        mListView.setOnRemovedItemListener(new SlideListItemListView3.
                OnRemovedItemListener() {
            @Override
            public void itemRemoved(int position, BaseAdapter adapter) {
                mDataList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
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
