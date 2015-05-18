package com.jp.materialdesignsample.activity.navigationdrawer.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public abstract class BaseNavigationDrawerParamFragment<E extends Parcelable> extends BaseNavigationDrawerFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            E param = bundle.getParcelable(KEY_PARAM);

            loadParam(param);
        }

        loadData();
    }

    protected abstract void loadParam(E param);
}
