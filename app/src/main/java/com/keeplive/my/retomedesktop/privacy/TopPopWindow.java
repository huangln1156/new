package com.keeplive.my.retomedesktop.privacy;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.keeplive.my.retomedesktop.R;

/**
 * Created by scx on 2020/6/21.
 */

public class TopPopWindow extends PopupWindow {

    private View mView;
    private TextView meun_one, meun_two;

    public TopPopWindow(Context context, View.OnClickListener paramOnClickListener,
                        int paramInt1, int paramInt2) {
        mView = LayoutInflater.from(context).inflate(R.layout.popwindow_topright, null);
        meun_one =  mView.findViewById(R.id.meun_one);
        meun_two =  mView.findViewById(R.id.meun_two);
        if (paramOnClickListener != null) {
            //设置点击监听
            meun_one.setOnClickListener(paramOnClickListener);
            meun_two.setOnClickListener(paramOnClickListener);
            setContentView(mView);
            //设置宽度
            setWidth(paramInt1);
            //设置高度
            setHeight(paramInt2);
            //设置显示隐藏动画
          //  setAnimationStyle(R.style.AnimTools);
            //设置背景透明
            setBackgroundDrawable(new ColorDrawable(0));
        }
    }


}
