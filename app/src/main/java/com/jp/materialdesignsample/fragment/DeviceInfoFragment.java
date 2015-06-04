package com.jp.materialdesignsample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.component.DeviceInformation;

public class DeviceInfoFragment extends BaseNavigationDrawerFragment {
    private DeviceInformation mDeviceInfo;
    private TextView mDensityText;
    private TextView mScreenSizeText;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_device_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDeviceInfo = new DeviceInformation(getActivity());
    }

    @Override
    protected void bindView(View rootView) {
        mDensityText = (TextView) rootView.findViewById(R.id.screen_info_density_text);
        mScreenSizeText = (TextView) rootView.findViewById(R.id.screen_info_size_text);
    }

    @Override
    protected void loadData() {
        mDensityText.setText(String.format("%s (%d)", mDeviceInfo.getDensityString(), mDeviceInfo.getDensity()));
        mScreenSizeText.setText(String.format("%s (w:%d h:%d)", mDeviceInfo.getScreenLayout(), mDeviceInfo.getScreenSize().x, mDeviceInfo.getScreenSize().y));
    }
}
