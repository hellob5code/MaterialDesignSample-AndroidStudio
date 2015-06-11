package com.jp.materialdesignsample.component.googleservice.googledrive;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.drive.Metadata;

public class DataAdapter extends BaseDataAdapter {
    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResource() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected Object bindViewHolder(View view) {
        DataViewHolder viewHolder = new DataViewHolder();
        viewHolder.mText = (TextView) view.findViewById(android.R.id.text1);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, Metadata data) {
        DataViewHolder userViewHolder = (DataViewHolder) viewHolder;

        userViewHolder.mText.setText(data.getTitle());
    }

    private static class DataViewHolder {
        public TextView mText;
    }
}
