package com.jp.materialdesignsample.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.adapter.SingleLineListAdapter;
import com.jp.materialdesignsample.fragment.googleservice.GoogleServiceSampleFragment;
import com.jp.materialdesignsample.fragment.material.MaterialSampleFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerMenuFragment extends BaseNavigationDrawerFragment implements AdapterView.OnItemClickListener {
    private ListView menuList;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_drawer_menu;
    }

    @Override
    protected void bindView(View rootView) {
        menuList = (ListView) rootView.findViewById(R.id.drawer_menu_list);
        menuList.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        SingleLineListAdapter adapter = new SingleLineListAdapter(getActivity(), createList());

        menuList.setAdapter(adapter);
    }

    private List<String> createList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Device Info");
        list.add("Material Samples");
        list.add("Database Sample");
        list.add("Service Sample");
        list.add("Google Service Sample");
        list.add("Network Status");

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                navigateTo(new DeviceInfoFragment(), true, null);
                break;
            case 1:
                navigateTo(new MaterialSampleFragment(), true, null);
                break;
            case 2:
                navigateTo(new DatabaseSampleFragment(), true, null);
                break;
            case 3:
                navigateTo(new ServiceSampleFragment(), true, null);
                break;
            case 4:
                navigateTo(new GoogleServiceSampleFragment(), true, null);
                break;
            case 5:
                navigateTo(new NetworkStatusFragment(), true, null);
                break;
            default:
                break;
        }
    }
}
