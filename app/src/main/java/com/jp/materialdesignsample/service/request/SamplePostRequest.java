package com.jp.materialdesignsample.service.request;

import com.jp.materialdesignsample.service.base.IServiceRequest;

import java.util.HashMap;

public class SamplePostRequest implements IServiceRequest {
    @Override
    public HashMap<String, String> getRequestParam() {
        HashMap<String, String> param = new HashMap<>();
        param.put("email", "test@yahoo.com");
        param.put("device_id", "test");
        return param;
    }
}
