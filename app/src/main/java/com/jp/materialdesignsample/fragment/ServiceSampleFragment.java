package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.service.RestServiceManager;
import com.jp.materialdesignsample.service.ServiceConstant;
import com.jp.materialdesignsample.service.base.BaseRestResponse;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;
import com.jp.materialdesignsample.service.request.param.SamplePostRequestParam;
import com.jp.materialdesignsample.service.response.SampleGetResponse;
import com.jp.materialdesignsample.service.response.SamplePostResponse;

public class ServiceSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener, OnServiceResponseListener<BaseRestResponse> {
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
                RestServiceManager getServiceManager = new RestServiceManager(getActivity(), ServiceConstant.TAG_GET, ServiceConstant.URL_GET, SampleGetResponse.class, this);
                getServiceManager.executeGet();
                break;
            case R.id.post_button:
                RestServiceManager postServiceManager = new RestServiceManager(getActivity(), ServiceConstant.TAG_POST, ServiceConstant.URL_POST, SamplePostResponse.class, this);
                postServiceManager.executePost(new SamplePostRequestParam());
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponseFailed(String tag) {

    }

    @Override
    public void onResponseSuccess(String tag, BaseRestResponse response) {
    }

    @Override
    public void onParseError(String tag, String response) {
    }
}
