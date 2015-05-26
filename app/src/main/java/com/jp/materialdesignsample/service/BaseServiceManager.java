package com.jp.materialdesignsample.service;

import android.util.Log;

import com.jp.materialdesignsample.service.base.IServiceRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

public class BaseServiceManager {
    private String mUrl = "";
    private TextHttpResponseHandler mHandler;
    private String mAuthUsername = "user";
    private String mAuthPassword = "pass";

    public BaseServiceManager(String url) {
        mUrl = url;
        mHandler = new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("SERVICE FAILED", String.format("STATUS: %d RESPONSE: %s", statusCode, responseString));
                if (throwable != null) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("SERVICE RESPONSE", responseString);
            }
        };
    }

    public void getAsync() {
        Log.d("SERVICE REQUEST", String.format("URL: %s", mUrl));
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(mUrl, mHandler);
    }

    public void postAsync() {
        Log.d("SERVICE REQUEST", String.format("URL: %s", mUrl));
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(mUrl, mHandler);
    }

    public void postAsync(IServiceRequest request) {
        Log.d("SERVICE REQUEST", String.format("URL: %s", mUrl));
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth(mAuthUsername, mAuthPassword);
        client.post(mUrl, new RequestParams(request.getRequestParam()), mHandler);
    }
}
