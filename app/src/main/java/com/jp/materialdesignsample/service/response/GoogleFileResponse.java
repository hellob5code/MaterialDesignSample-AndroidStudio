package com.jp.materialdesignsample.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.materialdesignsample.domain.model.GoogleFileItem;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleFileResponse {
    @JsonProperty("kind")
    private String mKind;

    public String getKind() {
        return mKind;
    }

    @JsonProperty("etag")
    private String mTag;

    public String getTag() {
        return mTag;
    }

    @JsonProperty("items")
    private List<GoogleFileItem> mItems;

    public List<GoogleFileItem> getItems() {
        return mItems;
    }

}
