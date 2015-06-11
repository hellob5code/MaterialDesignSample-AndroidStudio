package com.jp.materialdesignsample.service.base;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseGoogleRequest extends BaseRequest {
    private IRequestParam mParam;
    private String mAccessToken;

    public BaseGoogleRequest(int method, String url, String token, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        mAccessToken = token;
    }

    public BaseGoogleRequest(String url, String token, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        mAccessToken = token;
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

        String tCredentials = mAccessToken;
        String tCredBase64 = Base64.encodeToString(tCredentials.getBytes(),
                Base64.DEFAULT).replace("\n", "");
        params.put("Authorization", "Bearer " + mAccessToken);

//        String auth = String.format("OAuth %s", mAccessToken);
//        params.put("Authorization", auth);
        return params;
    }
}