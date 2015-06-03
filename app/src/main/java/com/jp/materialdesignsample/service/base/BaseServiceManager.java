package com.jp.materialdesignsample.service.base;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;

public abstract class BaseServiceManager<T> implements Response.ErrorListener, Response.Listener<String> {
    private RequestQueue mRequestQueue;
    private String mUrl;
    private String mTag;
    private OnServiceResponseListener<T> mListener;

    public BaseServiceManager(Context context, String tag, String url, OnServiceResponseListener<T> listener) {
        mRequestQueue = Volley.newRequestQueue(context);
        mTag = tag;
        mUrl = url;
        mListener = listener;
    }

    public void executeGet() {
        BaseRequest request = createRequest(Request.Method.GET, mUrl, this, this);
        request.setTag(mTag);

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    public void executePost() {
        BaseRequest request = createRequest(Request.Method.POST, mUrl, this, this);
        request.setTag(mTag);

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    public void executePost(IRequestParam param) {
        BaseRequest request = createRequest(Request.Method.POST, mUrl, this, this);
        request.setTag(mTag);

        if (param != null) {
            request.addParam(param);
            Log.d("SERVICE REQUEST", String.format("TAG: %s PARAM: %s", mTag, param.getRequestParam().toString()));
        }

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (mListener != null) {
            mListener.onResponseFailed(mTag);
        }
        Log.d("SERVICE FAILED", String.format("TAG: %s ERROR CODE: %d", mTag, error.networkResponse.statusCode));
    }

    @Override
    public void onResponse(String responseString) {
        try {
            T response = parseResponse(responseString);
            if (mListener != null) {
                mListener.onResponseSuccess(mTag, response);
            }
            Log.d("SERVICE RESPONSE", String.format("TAG: %s RESPONSE: %s", mTag, responseString));
        } catch (Exception e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onParseError(mTag, responseString);
            }
            Log.d("SERVICE PARSE ERROR", String.format("TAG: %s RESPONSE: %s", mTag, responseString));
        }
    }

    protected abstract BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener);

    protected abstract T parseResponse(String response) throws Exception;

}
