package com.jp.materialdesignsample.utils;

import com.jp.materialdesignsample.dialog.IPickerDialogItem;
import com.jp.materialdesignsample.domain.model.PickerItem;
import com.jp.materialdesignsample.domain.model.SpinnerItem;
import com.jp.materialdesignsample.view.spinner.ISpinnerItem;

import java.util.ArrayList;
import java.util.List;

public class DummyBuilder {

    public static List<IPickerDialogItem> buildDummyPickerList() {
        List<IPickerDialogItem> data = new ArrayList<>();
        data.add(new PickerItem(1, "value 1"));
        data.add(new PickerItem(2, "value 2"));
        data.add(new PickerItem(3, "value 3"));
        data.add(new PickerItem(4, "value 4"));

        return data;
    }

    public static List<ISpinnerItem> buildDummySpinnerList() {
        List<ISpinnerItem> data = new ArrayList<>();
        data.add(new SpinnerItem(1, "value 1"));
        data.add(new SpinnerItem(2, "value 2"));
        data.add(new SpinnerItem(3, "value 3"));
        data.add(new SpinnerItem(4, "value 4"));
        data.add(new SpinnerItem(5, "value 5"));
        data.add(new SpinnerItem(6, "value 6"));
        data.add(new SpinnerItem(7, "value 7"));
        data.add(new SpinnerItem(8, "value 8"));
        data.add(new SpinnerItem(9, "value 9"));

        return data;
    }
}
