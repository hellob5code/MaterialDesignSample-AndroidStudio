package com.jp.materialdesignsample.dialog;

import android.content.Context;

public class SystemDialog extends BaseDialog {

    protected SystemDialog(Context context) {
        super(context);
    }

    protected SystemDialog(Context context, int theme) {
        super(context, theme);
    }

    protected SystemDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void initDialog(Context context) {
        super.initDialog(context);

        setButton(BUTTON_POSITIVE,"OK");
    }
}
