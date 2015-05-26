package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.Toast;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerParamFragment;
import com.jp.materialdesignsample.domain.model.User;

public class ObjectTransferSampleFragment extends BaseNavigationDrawerParamFragment<User> {
    @Override
    protected int getFragmentLayoutResource() {
        return 0;
    }

    @Override
    protected void bindView(View rootView) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void loadParam(User param) {
        Toast.makeText(getActivity(), param.getUsername(), Toast.LENGTH_LONG).show();
    }
}
