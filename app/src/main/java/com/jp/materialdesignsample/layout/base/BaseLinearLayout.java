package com.jp.materialdesignsample.layout.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public abstract class BaseLinearLayout extends LinearLayout {
    protected Context mContext;
    private View mRootView;

    public BaseLinearLayout(Context context) {
        super(context);
        initLayout(context);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        mContext = context;

        if (getLayoutResource() != 0) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mRootView = inflater.inflate(getLayoutResource(), this, true);

            bindView(mRootView);
        }
    }

    protected abstract void bindView(View rootView);

    protected abstract int getLayoutResource();

}
