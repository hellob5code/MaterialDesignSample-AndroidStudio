package com.jp.materialdesignsample.view.spinner;

import android.content.Context;
import android.util.AttributeSet;

import com.jp.materialdesignsample.R;

public class SampleSpinner extends BaseSpinner {
    @Override
    protected int getTitleTextViewId() {
        return R.id.spinner_item_title;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.spinner_sample_item;
    }

    public SampleSpinner(Context context) {
        super(context);
    }

    public SampleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SampleSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
