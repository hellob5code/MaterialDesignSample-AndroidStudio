package com.jp.materialdesignsample.layout.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseFrameLayout extends FrameLayout {
    protected Context mContext;
    protected View mRootView;

    protected abstract int getLayoutResource();

    public BaseFrameLayout(Context context) {
        super(context);
        initLayout(context);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
}
