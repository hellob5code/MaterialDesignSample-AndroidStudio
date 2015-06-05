package com.jp.materialdesignsample.domain.model;

import com.jp.materialdesignsample.view.spinner.ISpinnerItem;

public class SpinnerItem implements ISpinnerItem {
    private int mItemId;
    private String mItemValue;

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int itemId) {
        mItemId = itemId;
    }

    public String getItemValue() {
        return mItemValue;
    }

    public void setItemValue(String itemValue) {
        mItemValue = itemValue;
    }

    public SpinnerItem(int itemId, String itemValue) {
        mItemId = itemId;
        mItemValue = itemValue;
    }

    @Override
    public String getDisplayTitle() {
        return mItemValue;
    }
}
