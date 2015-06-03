package com.jp.materialdesignsample.fragment.material;

import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;

public class MaterialSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {
    @Override
    protected String getToolbarTitle() {
        return "Material Samples";
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_material_sample;
    }

    @Override
    protected void bindView(View rootView) {
        Button btnButtonSample = (Button) rootView.findViewById(R.id.material_button_sample_button);
        Button btnTextFieldSample = (Button) rootView.findViewById(R.id.material_text_field_sample_button);
        Button btnDialogSample = (Button) rootView.findViewById(R.id.material_dialog_sample_button);
        Button btnPickerSample = (Button) rootView.findViewById(R.id.material_picker_sample_button);
        Button btnImageLoadingSample = (Button) rootView.findViewById(R.id.material_image_loading_sample_button);

        btnButtonSample.setOnClickListener(this);
        btnTextFieldSample.setOnClickListener(this);
        btnDialogSample.setOnClickListener(this);
        btnPickerSample.setOnClickListener(this);
        btnImageLoadingSample.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.material_button_sample_button:
                navigateTo(new MaterialButtonSampleFragment(), null);
                break;
            case R.id.material_text_field_sample_button:
                navigateTo(new MaterialTextFieldSampleFragment(), null);
                break;
            case R.id.material_dialog_sample_button:
                navigateTo(new MaterialDialogSampleFragment(), null);
                break;
            case R.id.material_picker_sample_button:
                navigateTo(new MaterialPickerSampleFragment(), null);
                break;
            case R.id.material_image_loading_sample_button:
                navigateTo(new MaterialImageLoadingFragment(), null);
                break;
            default:
                break;
        }
    }
}
