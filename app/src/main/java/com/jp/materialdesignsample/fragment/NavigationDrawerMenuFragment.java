package com.jp.materialdesignsample.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.adapter.SingleLineListAdapter;
import com.jp.materialdesignsample.fragment.material.ImageLoadingPatternFragment;
import com.jp.materialdesignsample.fragment.material.MaterialButtonSampleFragment;
import com.jp.materialdesignsample.fragment.material.MaterialDialogSampleFragment;
import com.jp.materialdesignsample.fragment.material.MaterialTextFieldSampleFragment;

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
    protected void loadData(Bundle savedInstanceState) {
        SingleLineListAdapter adapter = new SingleLineListAdapter(getActivity(), createList());

        menuList.setAdapter(adapter);
    }

    private List<String> createList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Button Sample");
        list.add("Text field Sample");
        list.add("Dialog Sample");
        list.add("Image Loading Pattern");

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                navigateTo(new MaterialButtonSampleFragment(), true);
                break;
            case 1:
                navigateTo(new MaterialTextFieldSampleFragment(), true);
                break;
            case 2:
                navigateTo(new MaterialDialogSampleFragment(), true);
                break;
            case 3:
                navigateTo(new ImageLoadingPatternFragment(), true);
                break;
            default:
                break;
        }
    }
}
