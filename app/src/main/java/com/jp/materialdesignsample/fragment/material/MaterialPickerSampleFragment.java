package com.jp.materialdesignsample.fragment.material;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.dialog.DialogBuilder;

public class MaterialPickerSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {

    private Button mPickerButton;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_picker_sample;
    }

    @Override
    protected void bindView(View rootView) {
        mPickerButton = (Button) rootView.findViewById(R.id.pick_dialog_button);

        mPickerButton.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pick_dialog_button:
                showPickerDialog();
                break;
            default:
                break;
        }
    }

    private void showPickerDialog() {
        Dialog dialog = DialogBuilder.buildPickerDialog(getActivity(), "PickerDialog", new String[]{"value1", "value2", "value3", "value4"});
        dialog.show();
    }
}
