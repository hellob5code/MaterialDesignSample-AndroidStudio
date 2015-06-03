package com.jp.materialdesignsample.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResponseData {
    @JsonProperty("firstname")
    private String mFirstName;

    @JsonProperty("lastname")
    private String mLastName;

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

}
