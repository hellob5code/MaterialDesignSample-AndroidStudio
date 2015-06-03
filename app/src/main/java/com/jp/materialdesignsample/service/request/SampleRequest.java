package com.jp.materialdesignsample.service.request;

import com.android.volley.Response;
import com.jp.materialdesignsample.service.ServiceConstant;
import com.jp.materialdesignsample.service.base.BaseRequest;

public class SampleRequest extends BaseRequest {
    @Override
    protected String getAuthUsername() {
        return ServiceConstant.AUTH_USERNAME;
    }

    @Override
    protected String getAuthPassword() {
        return ServiceConstant.AUTH_PASSWORD;
    }

    public SampleRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public SampleRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }
}
