package com.jp.materialdesignsample.volley.base;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.Base64;

import java.util.Collections;
import java.util.Map;

public abstract class BaseRequest extends StringRequest {

    protected abstract String getAuthUsername();

    protected abstract String getAuthPassword();

    public BaseRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public BaseRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> requestParams = Collections.emptyMap();
        String creds = String.format("%s:%s", getAuthUsername(), getAuthPassword());
        String auth = String.format("Basic %s", Base64.encodeToString(creds.getBytes(), Base64.DEFAULT));
        requestParams.put("Authorization", auth);
        return requestParams;
    }
}
