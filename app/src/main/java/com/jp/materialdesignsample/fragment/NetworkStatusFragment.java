package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.network.OnNetworkChangeListener;
import com.jp.materialdesignsample.utils.NetworkUtils;

public class NetworkStatusFragment extends BaseNavigationDrawerFragment implements OnNetworkChangeListener {
    private TextView mWifiStatusText;
    private TextView mMobileStatusText;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_network_status;
    }

    @Override
    protected void bindView(View rootView) {
        mWifiStatusText = (TextView) rootView.findViewById(R.id.wifi_status_text);
        mMobileStatusText = (TextView) rootView.findViewById(R.id.mobile_status_text);
    }

    @Override
    protected void loadData() {
        updateNetworkStatus();

        NetworkUtils.registerNetworkChangeListener(getActivity(), this);
    }

    @Override
    public void onStop() {
        super.onStop();
        NetworkUtils.unregisterNetworkChangeListener(getActivity());
    }

    private void updateNetworkStatus() {
        if (NetworkUtils.isWifiConnected(getActivity())) {
            mWifiStatusText.setText("online");
        } else {
            mWifiStatusText.setText("offline");
        }

        if (NetworkUtils.isMobileConnected(getActivity())) {
            mMobileStatusText.setText("online");
        } else {
            mMobileStatusText.setText("offline");
        }
    }

    @Override
    public void onChanged() {
        updateNetworkStatus();
    }
}
