package com.jp.materialdesignsample.dialog.material;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.adapter.SingleLineListAdapter;
import com.jp.materialdesignsample.dialog.IPickerDialogItem;
import com.jp.materialdesignsample.dialog.OnDialogValueSelectedListener;
import com.jp.materialdesignsample.view.material.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class MaterialSelectionDialog<T extends IPickerDialogItem> extends BaseMaterialDialog implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View mTitleLayout;
    private TextView mTitleText;
    private ListView mListView;
    private MaterialButton mNegativeButton;

    private OnDialogValueSelectedListener<T> mListener;
    private List<T> mItemList;
    private int mSelectedPosition = -1;

    public MaterialSelectionDialog(Context context) {
        super(context);
    }

    protected MaterialSelectionDialog(Context context, int theme) {
        super(context, theme);
    }

    protected MaterialSelectionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected int getDialogLayoutResource() {
        return R.layout.dialog_material_selection;
    }

    @Override
    protected void bindDialogView(View rootView) {
        mTitleLayout = rootView.findViewById(R.id.dialog_title_layout);
        mTitleText = (TextView) rootView.findViewById(R.id.dialog_title_text);
        mListView = (ListView) rootView.findViewById(R.id.dialog_selection_list);
        mNegativeButton = (MaterialButton) rootView.findViewById(R.id.dialog_negative_button);

        initDialogData();
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
    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        switch (whichButton) {
            case BUTTON_NEGATIVE:
                mNegativeButton.setText(text);
                mNegativeButton.setVisibility(View.VISIBLE);
                mNegativeButton.setOnClickListener(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void setButton(int whichButton, CharSequence text) {
        if (whichButton == BUTTON_NEGATIVE) {
            super.setButton(whichButton, text);
        }
    }

    @Override
    public void setButton(int whichButton, int textId) {
        if (whichButton == BUTTON_NEGATIVE) {
            super.setButton(whichButton, textId);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_negative_button:
                onClick(this, BUTTON_NEGATIVE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            mSelectedPosition = position;
            mListener.onValueSelected(getTag(), mItemList.get(position));
        }

        onClick(this, BUTTON_NEGATIVE);
    }

    private void initDialogData() {
        setButton(BUTTON_NEGATIVE, "CANCEL");
    }

    public void setOnDialogValueSelectedListener(OnDialogValueSelectedListener<T> onDialogValueSelectedListener) {
        mListener = onDialogValueSelectedListener;
    }

    public void setSelectionData(List<T> dataList) {
        mItemList = dataList;

        String[] displayList = createDisplayList(dataList);
        if (displayList != null) {
            SingleLineListAdapter adapter = new SingleLineListAdapter(mContext, Arrays.asList(displayList));

            mListView.setOnItemClickListener(this);
            mListView.setAdapter(adapter);
        }
    }

    public T getSelectedItem() {
        if (mSelectedPosition != -1) {
            return mItemList.get(mSelectedPosition);
        }
        return null;
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
