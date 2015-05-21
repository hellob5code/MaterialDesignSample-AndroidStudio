package com.jp.materialdesignsample.dialog;

public interface OnDialogValueSelectedListener<T extends IPickerDialogItem> {
    void onValueSelected(String tag, T selectedValue);
}
