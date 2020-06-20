package com.keeplive.my.retomedesktop;

import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.keeplive.my.retomedesktop.wiget.CustomListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {
    private ListView testModuleListView;
    private String mString = "12.x";
    CustomListViewAdapter adapter;
    int position = 0;

    private String[][] itemStrings = new String[][]{
            {"1.xxxx", "A"},
            {"2.xxxx", "B"},
            {"3.xxxx", "C"},
            {"4.xxxxx", "D"},
            {"5.xxxxx", "E"},
            {"6.xxxxx", "M"},
            {"7.xxxxx", "F"},
            {"8.xxxx", "G"},
            {"9.xxxxx", "H"},
            {"10.xxxxx", "I"},
            {"11.xxxx", "G"},
            {"12.x", "QW"},
            {"13.x", "EE"},
            {"14.x", "DD"},
            {"15.x", "SS"},
            {"16.x", "AD"},
            {"17.x", "ADC"},
            {"18.x", "ABV"},
            {"19.x", "VB"},
            {"120.x", "CX"},
            {"121.x", "XA"},
            {"1211.x", "WQA"},
            {"121111.x", "XZA"}};
    private List<Map<String, Object>> mList = new ArrayList<>();

    private void bindEvents() {
        testModuleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.setSelectItem(position);
                adapter.notifyDataSetInvalidated();
            }
        });
    }

    private List<Map<String, Object>> initialModuleItem() {
        Map<String, Object> map;
        for (int i = 0; i < itemStrings.length; i++) {
            map = new HashMap<>();
            map.put("itemName", itemStrings[i][0]);
            map.put("action", itemStrings[i][1]);
            if (map.get("action").equals("DD")) {
                mList.add(0,map);
            } else {
                mList.add(map);
            }

        }

        return mList;

    }

    private void bindViews() {
        testModuleListView = findViewById(R.id.testModuleListView);
    }

    private void initListView() {
        List<Map<String, Object>> maps = initialModuleItem();
        adapter = new CustomListViewAdapter(this, maps, mString);
        testModuleListView.setAdapter(adapter);
        adapter.setSelectItem(0);

    }


    @Override
    protected void initView() {
        bindViews();
        initListView();
        bindEvents();
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_main;
    }


}
