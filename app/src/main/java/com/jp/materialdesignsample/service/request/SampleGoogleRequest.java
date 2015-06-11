package com.jp.materialdesignsample.service.request;

import com.android.volley.Response;
import com.jp.materialdesignsample.service.base.BaseGoogleRequest;

public class SampleGoogleRequest extends BaseGoogleRequest {
    public SampleGoogleRequest(int method, String url, String token, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, token, listener, errorListener);
    }

    public SampleGoogleRequest(String url, String token, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, token, listener, errorListener);
    }

    @Override
    protected String getAuthUsername() {
        return null;
    }

    @Override
    protected String getAuthPassword() {
        return null;
    }
}
