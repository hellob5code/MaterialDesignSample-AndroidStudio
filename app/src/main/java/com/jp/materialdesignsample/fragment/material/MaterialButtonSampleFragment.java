package com.jp.materialdesignsample.fragment.material;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.BaseNavigationDrawerFragment;

public class MaterialButtonSampleFragment extends BaseNavigationDrawerFragment {
    private ImageButton mFloatingBtn;

    @Override
    protected String getToolbarTitle() {
        return "Material Button Sample";
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_material_button_sample;
    }

    @Override
    protected void bindView(View rootView) {
        mFloatingBtn = (ImageButton) rootView.findViewById(R.id.floating_button);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
