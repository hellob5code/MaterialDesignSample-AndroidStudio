package com.jp.materialdesignsample.service.base;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public abstract class BaseServiceManager<T extends BaseResponse> {
    protected String mTag = "";
    private String mUrl = "";
    private String mAuthUsername = "";
    private String mAuthPassword = "";

    private ResponseHandlerInterface mHandler;

    public BaseServiceManager(String tag, String url, Class<T> responseType) {
        mTag = tag;
        mUrl = url;
        mHandler = createHttpResponseHandler(responseType);
    }

    public BaseServiceManager(String tag, String url, Class<T> responseType, String username, String password) {
        this(tag, url, responseType);
        mAuthUsername = username;
        mAuthPassword = password;
    }

    public void getAsync() {
        Log.d("SERVICE REQUEST", String.format("URL: %s", mUrl));
        AsyncHttpClient client = getHttpClient(mAuthUsername, mAuthPassword);
        client.get(mUrl, mHandler);
    }

    public void postAsync() {
        Log.d("SERVICE REQUEST", String.format("URL: %s", mUrl));
        AsyncHttpClient client = getHttpClient(mAuthUsername, mAuthPassword);
        client.post(mUrl, mHandler);
    }

    public void postAsync(IServiceRequest request) {
        Log.d("SERVICE REQUEST", String.format("URL: %s", mUrl));
        AsyncHttpClient client = getHttpClient(mAuthUsername, mAuthPassword);
        client.post(mUrl, new RequestParams(request.getRequestParam()), mHandler);
    }

    public String getAuthUsername() {
        return mAuthUsername;
    }

    public String getAuthPassword() {
        return mAuthPassword;
    }

    public void setAuthUsername(String username) {
        mAuthUsername = username;
    }

    public void setAuthPassword(String password) {
        mAuthPassword = password;
    }

    protected AsyncHttpClient getHttpClient(String authUsername, String authPassword) {
        AsyncHttpClient client = new AsyncHttpClient();
        if (!authUsername.equals("") && !authPassword.equals("")) {
            client.setBasicAuth(authUsername, authPassword);
        }
        return client;
    }

    protected abstract ResponseHandlerInterface createHttpResponseHandler(Class<T> responseType);
}
