package com.keeplive.my.retomedesktop;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import com.keeplive.my.retomedesktop.receiver.NetBroadcastReceiver;


public class DesktopApplication extends Application {
    public static Context applicationContext;
    public static String MODEL = null;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        //动态注册网络变化的广播
        NetBroadcastReceiver mNetBroadcastReceiver = new NetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mNetBroadcastReceiver, intentFilter);
        MODEL = android.os.Build.MODEL;

    }
}
