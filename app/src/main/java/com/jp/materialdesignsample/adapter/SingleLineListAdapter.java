package com.jp.materialdesignsample.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.adapter.base.BaseListAdapter;

import java.util.List;

/**
 * Created by tuu.phung on 17/04/2015.
 */
public class SingleLineListAdapter extends BaseListAdapter<String> {

    public SingleLineListAdapter(Context context, List<String> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.list_single_line_text;
    }

    @Override
    protected Object bindViewHolder(View view) {
        SingleLineRowViewHolder viewHolder = new SingleLineRowViewHolder();

        viewHolder.mText = (TextView) view.findViewById(R.id.m_list_row_text);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, String text) {
        SingleLineRowViewHolder rowViewHolder = (SingleLineRowViewHolder) viewHolder;
        rowViewHolder.mText.setText(text);
    }

    private static class SingleLineRowViewHolder {
        public TextView mText;
    }
}
