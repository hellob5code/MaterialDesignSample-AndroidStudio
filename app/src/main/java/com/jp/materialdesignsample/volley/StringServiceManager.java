package com.jp.materialdesignsample.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class StringServiceManager extends BaseServiceManager<String> {
    public StringServiceManager(Context context) {
        super(context);
    }

    @Override
    protected Request<String> InitRequest(int requestMethod, String url, String tag) {
        StringRequest request = new StringRequest(requestMethod, url, this, this);
        request.setTag(tag);

        return request;
    }
}
