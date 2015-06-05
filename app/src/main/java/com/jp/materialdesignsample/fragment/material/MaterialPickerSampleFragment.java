package com.jp.materialdesignsample.fragment.material;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.dialog.DialogBuilder;
import com.jp.materialdesignsample.dialog.IPickerDialogItem;
import com.jp.materialdesignsample.dialog.OnDialogValueSelectedListener;
import com.jp.materialdesignsample.utils.DummyBuilder;

import java.util.List;

public class MaterialPickerSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener, OnDialogValueSelectedListener {

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_picker_sample;
    }

    @Override
    protected void bindView(View rootView) {
        Button pickerButton = (Button) rootView.findViewById(R.id.pick_dialog_button);

        pickerButton.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onStop() {
        super.onStop();
        DialogBuilder.clear();
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
        List<IPickerDialogItem> data = DummyBuilder.buildDummyPickerList();

        Dialog dialog = DialogBuilder.buildMaterialSelectionDialog(getActivity(), "PickerDialog", data, this);
        dialog.setTitle("Select item:");
        dialog.show();
    }

    @Override
    public void onValueSelected(String tag, int position) {
    }
}
