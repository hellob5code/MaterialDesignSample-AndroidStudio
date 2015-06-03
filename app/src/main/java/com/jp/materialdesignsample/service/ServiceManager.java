package com.jp.materialdesignsample.service;

import android.content.Context;

import com.jp.materialdesignsample.service.base.BaseServiceManager;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;

public class ServiceManager extends BaseServiceManager<String> {
    public ServiceManager(Context context, String tag, String url, OnServiceResponseListener<String> listener) {
        super(context, tag, url, listener);
    }

    @Override
    protected String parseResponse(String response) throws Exception {
        return response;
    }
}
