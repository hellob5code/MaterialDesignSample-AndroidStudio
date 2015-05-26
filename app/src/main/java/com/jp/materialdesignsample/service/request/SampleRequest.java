package com.jp.materialdesignsample.service.request;

import com.jp.materialdesignsample.service.base.IServiceRequest;

import java.util.HashMap;

/**
 * Created by tuu.phung on 26/05/2015.
 */
public class SampleRequest implements IServiceRequest {
    @Override
    public HashMap<String, String> getRequestParam() {
        HashMap<String, String> param = new HashMap<>();
        param.put("email", "test@yahoo.com");
        param.put("device_id", "test");
        return param;
    }
}
