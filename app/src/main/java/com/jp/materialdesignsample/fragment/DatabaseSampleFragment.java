package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.adapter.UserListAdapter;
import com.jp.materialdesignsample.domain.helper.ActiveAndroidDatabaseHelper;
import com.jp.materialdesignsample.domain.model.User;
import com.jp.materialdesignsample.view.material.MaterialButton;

import java.util.List;

public class DatabaseSampleFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {
    private ListView mUserList;
    private EditText mUsernameEdtext;
    private UserListAdapter mListAdapter;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_database_sample;
    }

    @Override
    protected void bindView(View rootView) {
        mUsernameEdtext = (EditText) rootView.findViewById(R.id.username_edtext);
        mUserList = (ListView) rootView.findViewById(R.id.user_list);
        MaterialButton mAddButton = (MaterialButton) rootView.findViewById(R.id.add_button);
        MaterialButton mRemoveButton = (MaterialButton) rootView.findViewById(R.id.remove_button);

        mAddButton.setOnClickListener(this);
        mRemoveButton.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        List<User> userList = ActiveAndroidDatabaseHelper.getList(User.class);
        mListAdapter = new UserListAdapter(getActivity(), userList);

        mUserList.setAdapter(mListAdapter);
        mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("user", mListAdapter.getItem(position));
//
//                ObjectTransferSampleFragment fragment = new ObjectTransferSampleFragment();
//                fragment.setArguments(bundle);

                navigateTo(new ObjectTransferSampleFragment(), mListAdapter.getItem(position));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                addUser();
                break;
            case R.id.remove_button:
                removeUser();
                break;
            default:
                break;
        }
    }

    private void addUser() {
        if (!mUsernameEdtext.getText().toString().equals("")) {
            User user = new User();
            user.setUserId("id");
            user.setUsername(mUsernameEdtext.getText().toString());
            user.setPassword(mUsernameEdtext.getText().toString());

            ActiveAndroidDatabaseHelper.saveItem(user);

            mListAdapter.add(user);
            mListAdapter.notifyDataSetChanged();
        }
    }

    private void removeUser() {
        if (mListAdapter.getCount() > 0) {
            User user = mListAdapter.getItem(0);
            if (ActiveAndroidDatabaseHelper.removeItem(User.class, user)) {
                mListAdapter.remove(user);
                mListAdapter.notifyDataSetChanged();
            }
        }
    }
}
