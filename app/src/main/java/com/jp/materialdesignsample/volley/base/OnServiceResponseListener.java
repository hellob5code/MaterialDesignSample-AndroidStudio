package com.jp.materialdesignsample.volley.base;

public interface OnServiceResponseListener<T> {
    void onResponseSuccess(String tag, T response);

    void onResponseFailed(String tag);

    void onSerializeException(String tag, String responseString);

}
