package com.jp.materialdesignsample.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SelectionDialog<T extends IPickerDialogItem> extends BaseDialog implements AdapterView.OnItemClickListener {
    private OnDialogValueSelectedListener<T> mListener;
    private List<T> mItemList;
    private int mSelectedPosition = -1;

    protected SelectionDialog(Context context) {
        super(context);
    }

    protected SelectionDialog(Context context, int theme) {
        super(context, theme);
    }

    protected SelectionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnDialogValueSelectedListener(OnDialogValueSelectedListener<T> onDialogValueSelectedListener) {
        mListener = onDialogValueSelectedListener;
    }

    public void setSelectionData(List<T> dataList) {
        mItemList = dataList;

        String[] displayList = createDisplayList(dataList);
        if (displayList != null) {
            ListView listView = initListView(displayList);
            setView(listView);
        }
    }

    public T getSelectedItem() {
        if (mSelectedPosition != -1) {
            return mItemList.get(mSelectedPosition);
        }
        return null;
    }

    protected ListView initListView(String[] displayList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, displayList);

        ListView listView = new ListView(mContext);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        return listView;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            mSelectedPosition = position;
            mListener.onValueSelected(getTag(), mItemList.get(position));
        }

        dismiss();
    }
}
