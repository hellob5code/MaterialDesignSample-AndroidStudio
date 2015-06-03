package com.jp.materialdesignsample.service;

import android.content.Context;

import com.android.volley.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.materialdesignsample.service.base.BaseRequest;
import com.jp.materialdesignsample.service.base.BaseRestResponse;
import com.jp.materialdesignsample.service.base.BaseServiceManager;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;
import com.jp.materialdesignsample.service.request.SampleRequest;

public class RestServiceManager extends BaseServiceManager<BaseRestResponse> {
    private Class<?> mType;

    public RestServiceManager(Context context, String tag, String url, Class<?> responseType, OnServiceResponseListener<BaseRestResponse> listener) {
        super(context, tag, url, listener);

        mType = responseType;
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new SampleRequest(method, url, listener, errorListener);
    }

    @Override
    protected BaseRestResponse parseResponse(String response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return (BaseRestResponse) mapper.readValue(response, mType);
    }
}
