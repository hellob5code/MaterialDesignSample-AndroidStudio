package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.service.BaseServiceManager;
import com.jp.materialdesignsample.service.ServiceUrl;
import com.jp.materialdesignsample.service.request.SampleRequest;

public class ServiceSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_service_sample;
    }

    @Override
    protected void bindView(View rootView) {
        Button getBtn = (Button) rootView.findViewById(R.id.get_button);
        Button postBtn = (Button) rootView.findViewById(R.id.post_button);

        getBtn.setOnClickListener(this);
        postBtn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_button:
                BaseServiceManager getService = new BaseServiceManager(ServiceUrl.GET_URL);
                getService.getAsync();
                break;
            case R.id.post_button:
                BaseServiceManager postService = new BaseServiceManager(ServiceUrl.POST_URL);
                postService.postAsync(new SampleRequest());
                break;
            default:
                break;
        }
    }
}
