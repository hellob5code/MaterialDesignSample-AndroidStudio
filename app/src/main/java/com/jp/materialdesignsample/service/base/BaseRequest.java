package com.jp.materialdesignsample.service.base;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.jp.materialdesignsample.service.ServiceConstant;
import com.loopj.android.http.Base64;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest extends StringRequest {
    private IRequestParam mParam;

    protected abstract String getAuthUsername();

    protected abstract String getAuthPassword();

    public BaseRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public BaseRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public void addParam(IRequestParam param) {
        mParam = param;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mParam != null) {
            return mParam.getRequestParam();
        }
        return super.getParams();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> params = new HashMap<>();
        String creds = String.format("%s:%s", getAuthUsername(), getAuthPassword());
        String auth = String.format("Basic %s", Base64.encodeToString(creds.getBytes(), Base64.DEFAULT));
        params.put("Authorization", auth);
        return params;
    }
}
