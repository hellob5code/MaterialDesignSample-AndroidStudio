package com.jp.materialdesignsample.utils;

import com.jp.materialdesignsample.dialog.IPickerDialogItem;
import com.jp.materialdesignsample.domain.model.PickerItem;

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
}
