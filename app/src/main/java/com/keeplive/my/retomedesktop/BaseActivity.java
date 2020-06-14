package com.keeplive.my.retomedesktop;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.keeplive.my.retomedesktop.wiget.DpPxUtil;
import com.keeplive.my.retomedesktop.wiget.LoadingDialog;


/**
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected LoadingDialog mLoadingDialog;
    protected Dialog messageDialog;

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        getDialogInstance();

    }

     //当没有网络时，点击不处理事件，且显示没有网络的提示
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                NetWorkUtil.checkNetwork();
                if (NetWorkUtil.checkNetwork() == Netwrok.NONE) {
                    showNoNetDialog();
                    return false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    public void showNoNetDialog() {
        if (null == messageDialog) {
            messageDialog = new Dialog(this, R.style.MessageDialog);
        }
        if (messageDialog.isShowing()) {
            return;
        }
        View dialogView = LayoutInflater.from(this).inflate(R.layout.public_dialog3, null);
        messageDialog.setContentView(dialogView);
        messageDialog.setCanceledOnTouchOutside(false);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.image_fork:
                    case R.id.button_confirm:
                        messageDialog.dismiss();
                        break;
                }
            }
        };
        //  ((TextView) dialogView.findViewById(R.id.tv_title)).setText(getResources().getString(R.string.dialog_title_xinxi));
        //  ((TextView) dialogView.findViewById(R.id.tv_message)).setText(getResources().getString(R.string.dialog_no_network));
        dialogView.findViewById(R.id.image_fork).setOnClickListener(onClickListener);
        dialogView.findViewById(R.id.button_confirm).setOnClickListener(onClickListener);
        messageDialog.show();
    }
//    private void showTip(View view, String tip) {
//        View popupWindowView = getLayoutInflater().inflate(R.layout.qqcp_qp, null);
//        TextView api_status = popupWindowView.findViewById(R.id.qp_get_api_status);
//        api_status.setText(tip);
//        final PopupWindow popupWindow = new PopupWindow(popupWindowView, DpPxUtil.getPxByDp(205), ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setFocusable(false);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//        popupWindow.setAnimationStyle(R.style.ShowNoticePopupAnimation);
//
//        int[] xy = new int[2];
//        view.getLocationOnScreen(xy);
//        popupWindow.showAsDropDown(view, -view.getWidth(), view.getHeight() / 10 - DpPxUtil.getPxByDp(6));
//
//    }
    public void netIsAvailable(Netwrok netEvent) {
        if (netEvent == Netwrok.NONE) {
            showNoNetDialog();
        } else {
            //从没网到有网
            if (null != messageDialog && messageDialog.isShowing()) {
                messageDialog.dismiss();
            }
        }
    }

    public void regsiter() {

    }

    public void getDialogInstance() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
    }

    public void showLoadingDialog() {

        if (mLoadingDialog.isShowing()) {
            return;
        }
        mLoadingDialog.setCancelable(true);
        if (!isFinishing()) {
            mLoadingDialog.show();
        }
    }

    public void closeLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }

    }


}
