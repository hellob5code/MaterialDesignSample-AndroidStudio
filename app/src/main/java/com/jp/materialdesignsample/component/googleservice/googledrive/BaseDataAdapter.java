package com.jp.materialdesignsample.component.googleservice.googledrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.widget.DataBufferAdapter;

public abstract class BaseDataAdapter extends DataBufferAdapter<Metadata> {
    protected Context mContext;

    public BaseDataAdapter(Context context) {
        super(context, -1);
        mContext = context;
    }

    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = View.inflate(getContext(), android.R.layout.simple_list_item_1, null);
//        }
//        Metadata metadata = getItem(position);
//        TextView titleTextView = (TextView) convertView.findViewById(android.R.id.text1);
//        titleTextView.setText(metadata.getTitle());
//        return convertView;
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Object viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(getItemLayoutResource(), parent, false);

            viewHolder = bindViewHolder(view);

            view.setTag(viewHolder);
        } else {
            view = convertView;

            viewHolder = view.getTag();
        }

        loadData(viewHolder, getItem(position));

        return view;
    }

    protected abstract int getItemLayoutResource();

    protected abstract Object bindViewHolder(View view);

    protected abstract void loadData(Object viewHolder, Metadata data);
}
