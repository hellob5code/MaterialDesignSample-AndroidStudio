package com.jp.materialdesignsample.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetResponseData {
    @JsonProperty("imgUrl")
    private String mImageUrl;

    @JsonProperty("detailUrl")
    private String mDetailUrl;

    @JsonProperty("teamTxt")
    private String mName;
}
