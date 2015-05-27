package com.jp.materialdesignsample.service;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.materialdesignsample.service.base.BaseResponse;
import com.jp.materialdesignsample.service.base.BaseServiceManager;
import com.jp.materialdesignsample.service.base.OnServiceResponseListener;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.io.IOException;

public class SampleServiceManager<T extends BaseResponse> extends BaseServiceManager<T> {
    private OnServiceResponseListener mListener;

    public SampleServiceManager(String tag, String url, Class responseType) {
        super(tag, url, responseType);
    }

    public SampleServiceManager(String tag, String url, Class responseType, String username, String password) {
        super(tag, url, responseType, username, password);
    }

    public void setOnServiceResponseListener(OnServiceResponseListener listener) {
        mListener = listener;
    }

    @Override
    protected ResponseHandlerInterface createHttpResponseHandler(final Class<T> responseType) {
        return new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("SERVICE FAILED", String.format("STATUS: %d RESPONSE: %s", statusCode, responseString));
                if (throwable != null) {
                    throwable.printStackTrace();
                }

                if (mListener != null) {
                    mListener.onResponseFailed(mTag);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("SERVICE RESPONSE", responseString);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    T data = mapper.readValue(responseString, responseType);
                    if (mListener != null) {
                        mListener.onResponseSuccess(mTag, data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (mListener != null) {
                        mListener.onSerializeException(mTag, responseString);
                    }
                }
            }
        };
    }
}
