package com.keeplive.my.retomedesktop.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.keeplive.my.retomedesktop.NetWorkUtil;
import com.keeplive.my.retomedesktop.Netwrok;

/**
 * Created by androidshuai on 2018/1/5.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    private OnNetworkChangeLisneter mOnNetworkChangeLisneter;

    public void setOnNetworkChangeLisneter(OnNetworkChangeLisneter mOnNetworkChangeLisneter) {
        this.mOnNetworkChangeLisneter = mOnNetworkChangeLisneter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Netwrok netwrok = NetWorkUtil.checkNetwork();
            switch (netwrok){
                case GPRS:
                    Toast.makeText(context, "正在使用流量", Toast.LENGTH_SHORT).show();
                    break;
                case NONE:
                    Toast.makeText(context, "网络已断开", Toast.LENGTH_SHORT).show();
                    break;
                case WIFI:
                    Toast.makeText(context, "正在使用wifi", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    }
}