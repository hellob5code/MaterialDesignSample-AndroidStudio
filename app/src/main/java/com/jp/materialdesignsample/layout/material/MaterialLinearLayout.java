package com.jp.materialdesignsample.layout.material;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jp.materialdesignsample.layout.base.BaseLinearLayout;

public class MaterialLinearLayout extends BaseLinearLayout {
    public MaterialLinearLayout(Context context) {
        super(context);
    }

    public MaterialLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView(View rootView) {

    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }
}
