package com.jp.materialdesignsample.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jp.materialdesignsample.service.base.BaseResponse;
import com.jp.materialdesignsample.volley.base.OnServiceResponseListener;

public abstract class BaseServiceManager<T extends BaseResponse> implements Response.ErrorListener, Response.Listener<String>, RequestQueue.RequestFinishedListener<String> {
    private RequestQueue mRequestQueue;
    private OnServiceResponseListener<T> mListener;

    public BaseServiceManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.addRequestFinishedListener(this);
    }

    public void setOnServiceResponseListener(OnServiceResponseListener<T> listener) {
        mListener = listener;
    }

    private void addToRequestQueue(int requestMethod, String url, String tag) {
        StringRequest request = InitRequest(requestMethod, url, tag);
        mRequestQueue.add(request);
    }

    protected abstract StringRequest InitRequest(int requestMethod, String url, String tag);

    public void addGetRequest(String requestTag, String url) {
        addToRequestQueue(Request.Method.GET, url, requestTag);
    }

    public void excuteGet(String requestTag, String url) {
        addGetRequest(requestTag, url);
        excute();
    }

    public void excute() {
        mRequestQueue.start();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("SERVICE ERROR", "");
        if (mListener != null) {
            mListener.onResponseFailed("");
        }
    }

    @Override
    public void onResponse(String responseString) {
        Log.d("SERVICE RESPONSE", responseString);

        try {
            T response = parseResponse(responseString);
            if (mListener != null) {
                mListener.onResponseSuccess("", response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onSerializeException("", responseString);
            }
        }
    }

    @Override
    public void onRequestFinished(Request<String> request) {
        Log.d("SERVICE FINISHED", request.getTag().toString());
    }

    protected abstract T parseResponse(String responseString);
}
