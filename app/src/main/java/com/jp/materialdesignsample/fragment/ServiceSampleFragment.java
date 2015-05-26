package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;

public class ServiceSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_service_sample;
    }

    @Override
    protected void bindView(View rootView) {
        Button postBtn = (Button) rootView.findViewById(R.id.post_button);

        postBtn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_button:
                break;
            default:
                break;
        }
    }
}
