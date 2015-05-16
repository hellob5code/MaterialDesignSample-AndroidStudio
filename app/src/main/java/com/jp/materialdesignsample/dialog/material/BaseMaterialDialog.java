package com.jp.materialdesignsample.dialog.material;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.jp.materialdesignsample.dialog.BaseDialog;

public abstract class BaseMaterialDialog extends BaseDialog {

    protected abstract int getDialogLayoutResource();

    protected BaseMaterialDialog(Context context) {
        super(context);
    }

    protected BaseMaterialDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseMaterialDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void initDialog(Context context) {
        super.initDialog(context);

        if (getDialogLayoutResource() != 0) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = inflater.inflate(getDialogLayoutResource(), null, false);
            setView(rootView);

            bindDialogView(rootView);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);

        dialog.dismiss();
    }

    protected abstract void bindDialogView(View rootView);
}
