package com.jp.materialdesignsample.fragment.material;

import android.view.View;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.adapter.SampleSpinnerAdapter;
import com.jp.materialdesignsample.layout.material.MaterialPickerLayout;
import com.jp.materialdesignsample.layout.material.MaterialTextLayout;
import com.jp.materialdesignsample.utils.DummyBuilder;
import com.jp.materialdesignsample.view.spinner.SampleSpinner;

public class MaterialLayoutSample extends BaseNavigationDrawerFragment {
    private MaterialTextLayout mTextLayout;
    private MaterialPickerLayout mPickerLayout;
    private SampleSpinner mSpinner;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_material_layout_sample;
    }

    @Override
    protected void bindView(View rootView) {
        mTextLayout = (MaterialTextLayout) rootView.findViewById(R.id.material_text_layout);
        mPickerLayout = (MaterialPickerLayout) rootView.findViewById(R.id.material_picker_layout);

        mSpinner = (SampleSpinner) rootView.findViewById(R.id.spinner_popup);
    }

    @Override
    protected void loadData() {
        mTextLayout.setPrimaryText("Title");
        mTextLayout.setSecondaryText("Value");

        mPickerLayout.setTitle("Picker layout");
        mPickerLayout.setHint("Select value");
        mPickerLayout.initPicker("SamplePicker", DummyBuilder.buildDummyPickerList(), null);
        mPickerLayout.setPickerTitle("Select value");

        mSpinner.setHint("Select value");
        mSpinner.setSpinnerAdapter(new SampleSpinnerAdapter(getActivity(), DummyBuilder.buildDummySpinnerList()));
    }

}
