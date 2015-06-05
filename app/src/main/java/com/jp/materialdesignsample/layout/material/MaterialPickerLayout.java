package com.jp.materialdesignsample.layout.material;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.dialog.DialogBuilder;
import com.jp.materialdesignsample.dialog.IPickerDialogItem;
import com.jp.materialdesignsample.dialog.OnDialogValueSelectedListener;
import com.jp.materialdesignsample.dialog.material.MaterialSelectionDialog;
import com.jp.materialdesignsample.layout.base.BaseLinearLayout;

import java.util.List;

public class MaterialPickerLayout extends BaseLinearLayout implements View.OnClickListener, DialogInterface.OnDismissListener {
    private TextView mTitleText;
    private TextView mValueText;
    private List<IPickerDialogItem> mSource;
    private int mSelectedItemPosition = -1;
    private MaterialSelectionDialog mDialog;

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_material_text;
    }

    public MaterialPickerLayout(Context context) {
        super(context);
    }

    public MaterialPickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialPickerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView(View rootView) {
        mTitleText = (TextView) rootView.findViewById(R.id.material_text_layout_primary_text);
        mValueText = (TextView) rootView.findViewById(R.id.material_text_layout_secondary_text);

        rootView.setOnClickListener(this);
    }

    public void setTitle(String text) {
        mTitleText.setText(text);
    }

    public void setTitle(int resId) {
        mTitleText.setText(resId);
    }

    public void setPickerTitle(String text) {
        if (mDialog != null && !text.equals("")) {
            mDialog.setTitle(text);
        }
    }

    public void setHint(String text) {
        if (mSelectedItemPosition == -1) {
            mValueText.setHint(text);
            mValueText.setText("");
        }
    }

    public void initPicker(String tag, List<IPickerDialogItem> source, OnDialogValueSelectedListener listener) {
        mDialog = (MaterialSelectionDialog) DialogBuilder.buildMaterialSelectionDialog(mContext, tag, source, listener);
        mDialog.setOnDismissListener(this);

        mSource = source;
    }

    public void setSelectedItemPosition(int position) {
        if (mSource != null && position >= 0 && position < mSource.size()) {
            mSelectedItemPosition = position;
            mValueText.setText(mSource.get(position).getDisplayText());
        }
    }

    public IPickerDialogItem getSelectedItem() {
        if (mSource != null && mSelectedItemPosition > -1) {
            return mSource.get(mSelectedItemPosition);
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        if (mDialog != null && mSource != null) {
            mDialog.show();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (dialog.equals(mDialog)) {
            setSelectedItemPosition(mDialog.getSelectedItemPosition());
        }
    }
}
