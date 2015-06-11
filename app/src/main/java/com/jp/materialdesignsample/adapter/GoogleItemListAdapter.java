package com.jp.materialdesignsample.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.domain.model.GoogleFileItem;
import com.jp.materialdesignsample.view.listview.BaseListAdapter;

import java.util.List;

public class GoogleItemListAdapter extends BaseListAdapter<GoogleFileItem> {
    public GoogleItemListAdapter(Context context, List<GoogleFileItem> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.list_single_line_text;
    }

    @Override
    protected Object bindViewHolder(View view) {
        GoogleItemViewHolder viewHolder = new GoogleItemViewHolder();

        viewHolder.mText = (TextView) view.findViewById(R.id.m_list_row_text);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, GoogleFileItem googleFileItem) {
        GoogleItemViewHolder rowViewHolder = (GoogleItemViewHolder) viewHolder;
        rowViewHolder.mText.setText(googleFileItem.getTitle());
    }

    private static class GoogleItemViewHolder {
        public TextView mText;
    }
}
