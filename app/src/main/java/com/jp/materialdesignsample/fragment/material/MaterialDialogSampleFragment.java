package com.jp.materialdesignsample.fragment.material;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.dialog.BaseDialog;
import com.jp.materialdesignsample.dialog.DialogBuilder;
import com.jp.materialdesignsample.dialog.OnDialogButtonClickListener;

public class MaterialDialogSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener, OnDialogButtonClickListener {
    private Button mSimpleSystemDialogButton;
    private Button mComplicatedSystemDialogButton;
    private Button mSimpleAlertDialog;
    private Button mComplicatedAlertDialog;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_material_dialog_sample;
    }

    @Override
    protected void bindView(View rootView) {
        mSimpleSystemDialogButton = (Button) rootView.findViewById(R.id.simple_system_dialog_button);
        mComplicatedSystemDialogButton = (Button) rootView.findViewById(R.id.complicated_system_dialog_button);
        mSimpleAlertDialog = (Button) rootView.findViewById(R.id.simple_alert_dialog_button);
        mComplicatedAlertDialog = (Button) rootView.findViewById(R.id.complicated_alert_dialog_button);

        mSimpleSystemDialogButton.setOnClickListener(this);
        mComplicatedSystemDialogButton.setOnClickListener(this);
        mSimpleAlertDialog.setOnClickListener(this);
        mComplicatedAlertDialog.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_system_dialog_button:
                BaseDialog simpleSystemDialog = DialogBuilder.buildSystemDialog(getActivity(), "SimpleSystemDialog", this);
                simpleSystemDialog.setMessage("This is simple system dialog.");
                simpleSystemDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK");

                simpleSystemDialog.show();
                break;
            case R.id.complicated_system_dialog_button:
                BaseDialog complicatedSystemDialog = DialogBuilder.buildSystemDialog(getActivity(), "ComplicatedSystemDialog", this);
                complicatedSystemDialog.setTitle("System Dialog");
                complicatedSystemDialog.setMessage("This is more complicated system dialog.");
                complicatedSystemDialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES");
                complicatedSystemDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO");
                complicatedSystemDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "SKIP");

                complicatedSystemDialog.setCanceledOnTouchOutside(false);

                complicatedSystemDialog.show();
                break;

            case R.id.simple_alert_dialog_button:
                BaseDialog simpleAlertDialog = DialogBuilder.buildMaterialAlertDialog(getActivity(), "SimpleAlertDialog", this);
                simpleAlertDialog.setMessage("This is simple material design alert dialog");

                simpleAlertDialog.show();
                break;

            case R.id.complicated_alert_dialog_button:
                BaseDialog complicatedAlertDialog = DialogBuilder.buildMaterialAlertDialog(getActivity(), "SimpleAlertDialog", this);
                complicatedAlertDialog.setTitle("Alert Dialog");
                complicatedAlertDialog.setMessage("This is more complicated material design alert dialog");
                complicatedAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES");
                complicatedAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO");

                complicatedAlertDialog.setCanceledOnTouchOutside(false);

                complicatedAlertDialog.show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onClick(String tag, int which) {
        Log.d("DIALOG", String.format("TAG: %s BUTTON: %d", tag, which));
    }
}
