package com.jp.materialdesignsample.domain.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleFileItem implements Parcelable {
    @JsonProperty("id")
    private String mId;

    public String getId() {
        return mId;
    }

    @JsonProperty("title")
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    @JsonProperty("webContentLink")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public GoogleFileItem() {

    }

    public GoogleFileItem(String id, String title, String url) {
        mId = id;
        mTitle = title;
        mUrl = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();

        bundle.putString("id", mId);
        bundle.putString("title", mTitle);
        bundle.putString("url", mUrl);

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<GoogleFileItem> CREATOR = new Creator<GoogleFileItem>() {

        @Override
        public GoogleFileItem createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();

            String id = bundle.getString("id");
            String title = bundle.getString("title");
            String url = bundle.getString("url");

            return new GoogleFileItem(id, title, url);
        }

        @Override
        public GoogleFileItem[] newArray(int size) {
            return new GoogleFileItem[size];
        }
    };
}
