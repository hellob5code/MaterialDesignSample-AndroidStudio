package com.jp.materialdesignsample.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.view.listview.BaseListAdapter;
import com.jp.materialdesignsample.domain.model.User;

import java.util.List;

public class UserListAdapter extends BaseListAdapter<User> {
    public UserListAdapter(Context context, List<User> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.list_single_line_text;
    }

    @Override
    protected Object bindViewHolder(View view) {
        UserViewHolder viewHolder = new UserViewHolder();
        viewHolder.mText = (TextView) view.findViewById(R.id.m_list_row_text);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, User user) {
        UserViewHolder userViewHolder = (UserViewHolder) viewHolder;

        userViewHolder.mText.setText(String.format("%s %s",user.getUsername(), user.getId().toString()));
    }

    private static class UserViewHolder {
        public TextView mText;
    }
}
