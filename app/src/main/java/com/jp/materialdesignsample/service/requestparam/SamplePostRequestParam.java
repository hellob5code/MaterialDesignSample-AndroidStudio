package com.jp.materialdesignsample.service.requestparam;

import com.jp.materialdesignsample.service.base.IRequestParam;

import java.util.HashMap;

public class SamplePostRequestParam implements IRequestParam {
    @Override
    public HashMap<String, String> getRequestParam() {
        HashMap<String, String> params = new HashMap<>();

        params.put("username", "test@yahoo.com");
        params.put("password", "123123");
        params.put("device_id", "android");
        params.put("operating_system", "android");

        return params;
    }
}
