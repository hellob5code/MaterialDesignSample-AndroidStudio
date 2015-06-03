package com.jp.materialdesignsample.service.listener;

public interface OnServiceResponseListener<T> {
    void onResponseFailed(String tag);

    void onResponseSuccess(String tag, T response);

    void onParseError(String tag, String response);
}
