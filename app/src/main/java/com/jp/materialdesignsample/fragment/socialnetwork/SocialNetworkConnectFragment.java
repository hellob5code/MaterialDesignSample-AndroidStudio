package com.jp.materialdesignsample.fragment.socialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.BaseNavigationDrawerFragment;

/**
 * Created by tuu.phung on 05/05/2015.
 */
public class SocialNetworkConnectFragment extends BaseNavigationDrawerFragment
        implements View.OnClickListener {

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_social_network_connect;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void bindView(View rootView) {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect_facebook_button:
                break;
            case R.id.connect_twitter_button:
                break;
        }
    }
}
