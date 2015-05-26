package com.jp.materialdesignsample.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jp.materialdesignsample.network.NetworkChangeReceiver;
import com.jp.materialdesignsample.network.OnNetworkChangeListener;

public class NetworkUtils {
    private static NetworkChangeReceiver mReceiver;
    private static boolean mIsReceiverRegistered = false;

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    }

    public static NetworkInfo getNetworkInfo(Context context, int networkType) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(networkType);
    }

    public static int getCurrentActiveNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().getType();
    }

    public static void registerNetworkChangeListener(Context context, OnNetworkChangeListener listener) {
        if (mReceiver == null) {
            mReceiver = new NetworkChangeReceiver();
        }

        unregisterNetworkChangeListener(context);

        if (!mIsReceiverRegistered) {
            mReceiver.setOnNetworkChangeListener(listener);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(mReceiver, intentFilter);
            mIsReceiverRegistered = true;
        }
    }

    public static void unregisterNetworkChangeListener(Context context) {
        if (mIsReceiverRegistered) {
            context.unregisterReceiver(mReceiver);
            mReceiver.setOnNetworkChangeListener(null);
            mIsReceiverRegistered = false;
        }
    }
}
