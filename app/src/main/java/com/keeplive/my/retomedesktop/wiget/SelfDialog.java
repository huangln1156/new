package com.keeplive.my.retomedesktop.wiget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


/**
 */

public class SelfDialog extends Dialog {
    private Context mContext;


    public SelfDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
       // 触摸外部弹窗
        if (isOutOfBounds(mContext, event)) {
           if (mOnclickOutsideListener!=null){
               mOnclickOutsideListener.onOutsideClick();
               return true;
           }
        }
        return super.onTouchEvent(event);
    }

    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }

    public void setOnclickOutsideListener(OnClickOutsideListener onclickOutsideListener) {
        mOnclickOutsideListener = onclickOutsideListener;
    }

    private OnClickOutsideListener mOnclickOutsideListener;

    public interface OnClickOutsideListener {
        void onOutsideClick();
    }
}
