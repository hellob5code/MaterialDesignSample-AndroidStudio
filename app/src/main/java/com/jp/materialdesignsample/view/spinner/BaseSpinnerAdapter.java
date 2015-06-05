package com.jp.materialdesignsample.view.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public abstract class BaseSpinnerAdapter<T extends ISpinnerItem> extends ArrayAdapter<T> {
    protected Context mContext;
    protected List<T> mListItem;

    public BaseSpinnerAdapter(Context context, List<T> itemList) {
        super(context, -1, itemList);
        mContext = context;
        mListItem = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        SpinnerViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(getItemLayoutResource(), parent, false);

            viewHolder = bindViewHolder(view);

            view.setTag(viewHolder);
        } else {
            view = convertView;

            viewHolder = (SpinnerViewHolder) view.getTag();
        }

        viewHolder.mTitleText.setText(mListItem.get(position).getDisplayTitle());

        return view;
    }

    private SpinnerViewHolder bindViewHolder(View view) {
        SpinnerViewHolder viewHolder = new SpinnerViewHolder();
        viewHolder.mTitleText = (TextView) view.findViewById(getItemTitleTextViewId());
        return viewHolder;
    }

    protected abstract int getItemLayoutResource();

    protected abstract int getItemTitleTextViewId();

    private class SpinnerViewHolder {
        public TextView mTitleText;
    }
}
