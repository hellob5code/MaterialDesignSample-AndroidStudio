package com.jp.materialdesignsample.adapter;

import android.content.Context;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.view.spinner.BaseSpinnerAdapter;
import com.jp.materialdesignsample.view.spinner.ISpinnerItem;

import java.util.List;

public class SampleSpinnerAdapter extends BaseSpinnerAdapter<ISpinnerItem> {
    public SampleSpinnerAdapter(Context context, List<ISpinnerItem> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.spinner_sample_item;
    }

    @Override
    protected int getItemTitleTextViewId() {
        return R.id.spinner_item_title;
    }
}
