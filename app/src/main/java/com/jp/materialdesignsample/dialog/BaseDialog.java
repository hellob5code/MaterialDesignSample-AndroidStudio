package com.jp.materialdesignsample.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;

public abstract class BaseDialog extends AlertDialog implements DialogInterface.OnClickListener {
    private String mTag = "";

    private OnDialogButtonClickListener mListener;

    protected Context mContext;

    protected BaseDialog(Context context) {
        super(context);
        initDialog(context);
    }

    protected BaseDialog(Context context, int theme) {
        super(context, theme);
        initDialog(context);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog(context);
    }

    protected void initDialog(Context context) {
        mContext = context;
    }

    @Override
    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        super.setButton(whichButton, text, listener);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mListener != null) {
            mListener.onClick(mTag, which);
        }
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getTag() {
        return mTag;
    }

    public void setOnDialogButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        mListener = onDialogButtonClickListener;
    }

    @Override
    public void setTitle(CharSequence title) {
        if (!title.equals("")) {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (titleId > 0) {
            setTitle(mContext.getResources().getString(titleId));
        }
    }

    public void setMessage(int messageId) {
        String message = messageId > 0 ? mContext.getResources().getString(messageId) : "";
        setMessage(message);
    }

    public void setButton(int whichButton, CharSequence text) {
        if (!text.equals("")) {
            setButton(whichButton, text, this);
        }
    }

    public void setButton(int whichButton, int textId) {
        if (textId > 0) {
            setButton(whichButton, mContext.getResources().getString(textId), this);
        }
    }
}
