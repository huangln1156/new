package com.keeplive.my.retomedesktop.wiget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.keeplive.my.retomedesktop.R;

/**
 * Created by scx on 2020/6/14.
 */

public class LoadingDialog extends Dialog implements View.OnClickListener {
    private OnCancelListener mOnCancelListener;
    private int layouId;
    private TextView mProgress_tv;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.FullScreenDialogAct);
        init();
    }

    private void init() {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.common_loading_dialog);
        setCanceledOnTouchOutside(false);
        mProgress_tv = findViewById(R.id.progress_tv);
    }

    public void setTv_Progress(String str) {
        mProgress_tv.setText(str);
    }

    @Override
    public void onClick(View v) {

    }
}
