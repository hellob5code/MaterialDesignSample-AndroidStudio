package com.jp.materialdesignsample.service;

import android.content.Context;

import com.android.volley.Response;
import com.jp.materialdesignsample.service.base.BaseRequest;
import com.jp.materialdesignsample.service.base.BaseServiceManager;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;
import com.jp.materialdesignsample.service.request.SampleRequest;

public class ServiceManager extends BaseServiceManager<String> {
    public ServiceManager(Context context, String tag, String url, OnServiceResponseListener<String> listener) {
        super(context, tag, url, listener);
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new SampleRequest(method, url, listener, errorListener);
    }

    @Override
    protected String parseResponse(String response) throws Exception {
        return response;
    }
}
