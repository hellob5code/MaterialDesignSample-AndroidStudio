package com.jp.materialdesignsample.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;

public class PickerDialog<T extends IPickerDialogItem> extends BaseDialog {
    private OnDialogValueSelectedListener<T> mListener;
    private List<T> mItemList;
    private NumberPicker mPicker;

    protected PickerDialog(Context context) {
        super(context);
    }

    protected PickerDialog(Context context, int theme) {
        super(context, theme);
    }

    protected PickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void initDialog(Context context) {
        super.initDialog(context);

        setButton(BUTTON_POSITIVE, "OK");
        setButton(BUTTON_NEGATIVE, "CANCEL");
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == BUTTON_POSITIVE && mListener != null) {
            if (mPicker != null) {
                int selectedPosition = mPicker.getValue();
                mListener.onValueSelected(getTag(), mItemList.get(selectedPosition));
                Toast.makeText(mContext, String.format("%s selected", mItemList.get(selectedPosition).getDisplayText()), Toast.LENGTH_SHORT).show();
            } else {
                mListener.onValueSelected(getTag(), null);
            }
        }
    }

    public void setOnDialogValueSelectedListener(OnDialogValueSelectedListener<T> onDialogValueSelectedListener) {
        mListener = onDialogValueSelectedListener;
    }

    public void setPickerData(List<T> dataList) {
        mItemList = dataList;

        String[] displayList = createDisplayList(dataList);
        if (displayList != null) {
            mPicker = new NumberPicker(mContext);
            mPicker.setMinValue(0);
            mPicker.setMaxValue(displayList.length - 1);
            mPicker.setDisplayedValues(displayList);
            mPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            
            setView(mPicker);
        }
    }

    public void setPosition(int position) {
        if (mPicker != null) {
            mPicker.setValue(position);
        }
    }

    private String[] createDisplayList(List<T> dataList) {
        if (dataList != null && dataList.size() > 0) {
            String[] displayList = new String[dataList.size()];
            for (int i = 0; i < dataList.size(); i++) {
                displayList[i] = dataList.get(i).getDisplayText();
            }

            return displayList;
        }

        return null;
    }
}
