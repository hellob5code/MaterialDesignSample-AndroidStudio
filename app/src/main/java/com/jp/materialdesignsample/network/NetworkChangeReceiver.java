package com.jp.materialdesignsample.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jp.materialdesignsample.utils.NetworkUtils;

public class NetworkChangeReceiver extends BroadcastReceiver {
    OnNetworkChangeListener mListener;

    public void setOnNetworkChangeListener(OnNetworkChangeListener onNetworkChangeListener) {
        mListener = onNetworkChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtils.isWifiConnected(context) ? "connected" : "disconnected";
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onChanged();
        }
    }
}
