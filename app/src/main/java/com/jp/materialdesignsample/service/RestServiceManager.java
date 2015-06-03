package com.jp.materialdesignsample.service;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.materialdesignsample.service.base.BaseRestResponse;
import com.jp.materialdesignsample.service.base.BaseServiceManager;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;

public class RestServiceManager extends BaseServiceManager<BaseRestResponse> {
    private Class<?> mType;

    public RestServiceManager(Context context, String tag, String url, Class<?> responseType, OnServiceResponseListener<BaseRestResponse> listener) {
        super(context, tag, url, listener);

        mType = responseType;
    }

    @Override
    protected BaseRestResponse parseResponse(String response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return (BaseRestResponse) mapper.readValue(response, mType);
    }
}
