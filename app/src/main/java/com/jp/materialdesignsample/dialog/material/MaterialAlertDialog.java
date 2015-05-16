package com.jp.materialdesignsample.dialog.material;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.view.material.MaterialButton;

public class MaterialAlertDialog extends BaseMaterialDialog implements View.OnClickListener {
    private View mTitleLayout;

    private TextView mTitleText;
    private TextView mMessageText;

    private MaterialButton mPositiveButton;
    private MaterialButton mNegativeButton;

    @Override
    protected int getDialogLayoutResource() {
        return R.layout.dialog_alert;
    }

    public MaterialAlertDialog(Context context) {
        super(context);
    }

    protected MaterialAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    protected MaterialAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void bindDialogView(View rootView) {
        mTitleLayout = rootView.findViewById(R.id.dialog_title_layout);

        mTitleText = (TextView) rootView.findViewById(R.id.dialog_title_text);
        mMessageText = (TextView) rootView.findViewById(R.id.dialog_message_text);

        mPositiveButton = (MaterialButton) rootView.findViewById(R.id.dialog_positive_button);
        mNegativeButton = (MaterialButton) rootView.findViewById(R.id.dialog_negative_button);
    }


    @Override
    public void setTitle(CharSequence title) {
        if (title == "") {
            mTitleLayout.setVisibility(View.GONE);
        } else {
            mTitleLayout.setVisibility(View.VISIBLE);
            mTitleText.setText(title);
        }
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessageText.setText(message);
    }

    @Override
    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        switch (whichButton) {
            case BUTTON_POSITIVE:
                mPositiveButton.setText(text);
                mPositiveButton.setOnClickListener(this);
                break;
            case BUTTON_NEGATIVE:
                mNegativeButton.setText(text);
                mNegativeButton.setVisibility(View.VISIBLE);
                mNegativeButton.setOnClickListener(this);
                break;
            case BUTTON_NEUTRAL:
                Log.d("ALERT DIALOG", "Neutral button is not supported.");
                break;
            default:
                break;
        }
    }

    @Override
    public void setButton(int whichButton, CharSequence text) {
        if (whichButton != BUTTON_NEUTRAL) {
            super.setButton(whichButton, text);
        } else {
            Log.d("ALERTDIALOG", "Neutral button is not supported.");
        }
    }

    @Override
    public void setButton(int whichButton, int textId) {
        if (whichButton != BUTTON_NEUTRAL) {
            super.setButton(whichButton, textId);
        } else {
            Log.d("ALERTDIALOG", "Neutral button is not supported.");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_positive_button:
                onClick(this, BUTTON_POSITIVE);
                break;
            case R.id.dialog_negative_button:
                onClick(this, BUTTON_NEGATIVE);
                break;
            default:
                break;
        }
    }
}
