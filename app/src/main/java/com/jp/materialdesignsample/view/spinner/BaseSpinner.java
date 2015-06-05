package com.jp.materialdesignsample.view.spinner;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jp.materialdesignsample.layout.base.BaseFrameLayout;

public abstract class BaseSpinner extends BaseFrameLayout implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView mTitleText;
    private ListPopupWindow mPopupWindow;
    private BaseSpinnerAdapter<ISpinnerItem> mSpinnerAdapter;
    private int mSelectedItemPosition = -1;

    protected abstract int getTitleTextViewId();

    public BaseSpinner(Context context) {
        super(context);
    }

    public BaseSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView(View rootView) {
        mTitleText = (TextView) findViewById(getTitleTextViewId());

        mPopupWindow = new ListPopupWindow(mContext);
        mPopupWindow.setAnchorView(rootView);
        mPopupWindow.setModal(true);

        rootView.setOnClickListener(this);
    }

    public void setHint(String text) {
        mTitleText.setHint(text);
    }

    public void setSpinnerAdapter(BaseSpinnerAdapter<ISpinnerItem> adapter) {
        mSpinnerAdapter = adapter;

        mPopupWindow.setAdapter(adapter);
        mPopupWindow.setOnItemClickListener(this);
    }

    public void setSelection(int position) {
        if (mSpinnerAdapter != null && mSpinnerAdapter.getCount() > 0) {
            mSelectedItemPosition = position;
            mTitleText.setText(mSpinnerAdapter.getItem(position).getDisplayTitle());
        }
    }

    public ISpinnerItem getSelectedItem() {
        if (mSelectedItemPosition != -1) {
            return mSpinnerAdapter.getItem(mSelectedItemPosition);
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mRootView)) {
            mPopupWindow.show();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mSelectedItemPosition = position;

        mTitleText.setText(mSpinnerAdapter.getItem(position).getDisplayTitle());
        mPopupWindow.dismiss();
    }
}
