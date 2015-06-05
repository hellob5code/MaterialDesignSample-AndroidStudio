package com.jp.materialdesignsample.layout.material;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.layout.base.BaseLinearLayout;

public class MaterialTextLayout extends BaseLinearLayout {
    private TextView mPrimaryText;
    private TextView mSecondaryText;

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_material_text;
    }

    public MaterialTextLayout(Context context) {
        super(context);
    }

    public MaterialTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView(View rootView) {
        mPrimaryText = (TextView) rootView.findViewById(R.id.material_text_layout_primary_text);
        mSecondaryText = (TextView) rootView.findViewById(R.id.material_text_layout_secondary_text);
    }

    public void setPrimaryText(String text) {
        mPrimaryText.setText(text);
    }

    public void setPrimaryText(int resId) {
        mPrimaryText.setText(resId);
    }

    public void setSecondaryText(String text) {
        mSecondaryText.setText(text);
    }

    public void setSecondaryText(int resId) {
        mSecondaryText.setText(resId);
    }
}
