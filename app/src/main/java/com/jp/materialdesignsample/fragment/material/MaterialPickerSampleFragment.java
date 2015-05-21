package com.jp.materialdesignsample.fragment.material;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.dialog.DialogBuilder;
import com.jp.materialdesignsample.dialog.OnDialogValueSelectedListener;
import com.jp.materialdesignsample.domain.model.PickerItem;

import java.util.ArrayList;
import java.util.List;

public class MaterialPickerSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener, OnDialogValueSelectedListener<PickerItem> {

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
        List<PickerItem> data = new ArrayList<>();
        data.add(new PickerItem(1, "value 1"));
        data.add(new PickerItem(2, "value 2"));
        data.add(new PickerItem(3, "value 3"));
        data.add(new PickerItem(4, "value 4"));

        Dialog dialog = DialogBuilder.buildPickerDialog(getActivity(), "PickerDialog", data, this);
        dialog.setTitle("Select item:");
        dialog.show();
    }

    @Override
    public void onValueSelected(String tag, PickerItem selectedValue) {

    }
}
