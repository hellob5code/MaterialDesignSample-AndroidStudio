package com.jp.materialdesignsample.service.base;

public interface OnServiceResponseListener {
    void onResponseSuccess(String tag, BaseResponse response);

    void onResponseFailed(String tag);

    void onSerializeException(String tag, String responseString);
}
