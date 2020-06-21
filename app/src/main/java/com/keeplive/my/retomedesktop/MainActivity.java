package com.keeplive.my.retomedesktop;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.keeplive.my.retomedesktop.privacy.TopPopWindow;
import com.keeplive.my.retomedesktop.wiget.CustomListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ListView testModuleListView;
    private ImageView more;
    private FrameLayout frame_layout;
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
                mList.add(0, map);
            } else {
                mList.add(map);
            }

        }

        return mList;

    }

    private void bindViews() {
        testModuleListView = findViewById(R.id.testModuleListView);
        frame_layout = findViewById(R.id.frame_layout);
        more = findViewById(R.id.more);
        more.setOnClickListener(this);
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
        return R.layout.activity_main;
    }

    TopPopWindow topPopWindow;

    /**
     * 显示右上角popup菜单
     */
    private void showTopRightPopMenu() {
        if (topPopWindow == null) {
            //(activity,onclicklistener,width,height)
            topPopWindow = new TopPopWindow(MainActivity.this, this, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            //监听窗口的焦点事件，点击窗口外面则取消显示

        }
        //设置默认获取焦点
        topPopWindow.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        topPopWindow.showAtLocation(more, Gravity.TOP | Gravity.END, more.getWidth(), frame_layout.getHeight());
        //如果窗口存在，则更新
        topPopWindow.update();
    }

    private void showPopBottom() {
        PopupWindow popupWindow = new PopupWindow(this);
        View content_view = LayoutInflater.from(this).inflate(R.layout.popwindow_topright, null);
        popupWindow.setContentView(content_view);
        TextView meun_one = content_view.findViewById(R.id.meun_one);
        TextView meun_two = content_view.findViewById(R.id.meun_two);
        meun_one.setOnClickListener(this);
        meun_two.setOnClickListener(this);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(null);
        content_view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //  popupWindow.showAsDropDown(more, -content_view.getMeasuredWidth() + more.getWidth(), -content_view.getMeasuredHeight() / 3);
        popupWindow.showAtLocation(more, Gravity.TOP | Gravity.END, more.getWidth(), frame_layout.getHeight());
        popupWindow.update();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more:
                showPopBottom();
                //showTopRightPopMenu();
                break;
            case R.id.meun_one:
                startActivity(new Intent(MainActivity.this, com.keeplive.my.retomedesktop.privacy.MainActivity.class));
                break;
            case R.id.meun_two:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
        }

    }
}
