package com.jp.materialdesignsample.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.service.SampleServiceManager;
import com.jp.materialdesignsample.service.ServiceConstant;
import com.jp.materialdesignsample.service.base.BaseResponse;
import com.jp.materialdesignsample.service.base.OnServiceResponseListener;
import com.jp.materialdesignsample.service.request.SamplePostRequest;
import com.jp.materialdesignsample.service.response.SampleGetResponse;
import com.jp.materialdesignsample.service.response.SamplePostResponse;

public class ServiceSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener, OnServiceResponseListener {
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
                SampleServiceManager<SampleGetResponse> getService = new SampleServiceManager<>(ServiceConstant.TAG_GET, ServiceConstant.URL_GET, SampleGetResponse.class);
                getService.setAuthUsername(ServiceConstant.AUTH_USERNAME);
                getService.setAuthPassword(ServiceConstant.AUTH_PASSWORD);
                getService.setOnServiceResponseListener(this);
                getService.getAsync();
                break;
            case R.id.post_button:
                SampleServiceManager<SamplePostResponse> postService = new SampleServiceManager<>(ServiceConstant.TAG_POST, ServiceConstant.URL_POST, SamplePostResponse.class, ServiceConstant.AUTH_USERNAME, ServiceConstant.AUTH_PASSWORD);
                postService.setOnServiceResponseListener(this);
                postService.postAsync(new SamplePostRequest());
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponseSuccess(String tag, BaseResponse response) {
        switch (tag) {
            case ServiceConstant.TAG_GET:
                SampleGetResponse getResponse = (SampleGetResponse) response;
                break;
            case ServiceConstant.TAG_POST:
                break;
        }
        Log.d(tag, response.getMessage());
    }

    @Override
    public void onResponseFailed(String tag) {

    }

    @Override
    public void onSerializeException(String tag, String responseString) {

    }
}
