package com.jp.materialdesignsample.service;

import android.content.Context;

import com.android.volley.Response;
import com.jp.materialdesignsample.service.base.BaseRequest;
import com.jp.materialdesignsample.service.base.BaseRestResponse;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;
import com.jp.materialdesignsample.service.request.SampleGoogleRequest;

public class GoogleServiceManager extends RestServiceManager {
    private String mAccessToken;

    public GoogleServiceManager(Context context, String tag, String url, String token, Class<?> responseType, OnServiceResponseListener<BaseRestResponse> listener) {
        super(context, tag, url, responseType, listener);
        mAccessToken = token;
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new SampleGoogleRequest(method, url, mAccessToken, listener, errorListener);
    }
}
