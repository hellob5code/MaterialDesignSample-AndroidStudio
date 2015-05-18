package com.jp.materialdesignsample.activity.navigationdrawer.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.jp.materialdesignsample.activity.navigationdrawer.OnNavigateListener;
import com.jp.materialdesignsample.fragment.base.BaseFragment;

public abstract class BaseNavigationDrawerFragment extends BaseFragment {
    protected static final String KEY_PARAM = "KEY_PARAM";

    private BaseNavigationDrawerActivity mNavigationActivity;

    private boolean mIsDrawerEnabled;

    protected String getToolbarTitle() {
        return "";
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mNavigationActivity = (BaseNavigationDrawerActivity) activity;

        mIsDrawerEnabled = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!getToolbarTitle().equals("")) {
            mNavigationActivity.updateToolbarOnFragmentStart(getToolbarTitle());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNavigationActivity.getDrawerEnabled() != mIsDrawerEnabled) {
            mNavigationActivity.setDrawerEnabled(mIsDrawerEnabled);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mNavigationActivity.updateToolbarOnFragmentStop();
    }

    protected void refreshFragment() {

    }

    protected final void navigateTo(BaseNavigationDrawerFragment fragment, @Nullable Parcelable param) {
        addParam(fragment, param);

        if (mNavigationActivity.isDrawerOpened()) {
            mNavigationActivity.closeDrawer();
            mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
                @Override
                public void onNavigate(BaseNavigationDrawerFragment frag) {
                    mNavigationActivity.navigateToFragment(frag);
                }
            }, fragment);
        } else {
            mNavigationActivity.navigateToFragment(fragment);
        }
    }


    protected final void navigateTo(BaseNavigationDrawerFragment fragment, final boolean isFirstLevelFragment, @Nullable Parcelable param) {
        addParam(fragment, param);

        if (mNavigationActivity.isDrawerOpened()) {
            mNavigationActivity.closeDrawer();

            mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
                @Override
                public void onNavigate(BaseNavigationDrawerFragment frag) {
                    if (isFirstLevelFragment) {
                        mNavigationActivity.navigateToFirstLevelFragment(frag);
                    } else {
                        mNavigationActivity.navigateToFragment(frag);
                    }
                }
            }, fragment);
        } else {
            if (isFirstLevelFragment) {
                mNavigationActivity.navigateToFirstLevelFragment(fragment);
            } else {
                mNavigationActivity.navigateToFragment(fragment);
            }
        }
    }

    protected final void navigateTo(BaseNavigationDrawerFragment fragment, final boolean isFirstLevelFragment, final boolean willRefresh, @Nullable final Parcelable param) {
        if (mNavigationActivity.isDrawerOpened()) {
            mNavigationActivity.closeDrawer();

            mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
                @Override
                public void onNavigate(BaseNavigationDrawerFragment frag) {
                    navigateTo(frag, isFirstLevelFragment, param);
                    if (willRefresh) {
                        frag.refreshFragment();
                    }
                }
            }, fragment);
        } else {
            navigateTo(fragment, isFirstLevelFragment, param);
            if (willRefresh) {
                fragment.refreshFragment();
            }
        }
    }

    protected final void navigateBack() {
        if (mNavigationActivity.isDrawerOpened()) {
            mNavigationActivity.closeDrawer();
            mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
                @Override
                public void onNavigate(BaseNavigationDrawerFragment frag) {
                    mNavigationActivity.navigateBack();
                }
            }, null);
        } else {
            mNavigationActivity.navigateBack();
        }
    }

    protected final void navigateBack(final int count) {
        if (mNavigationActivity.isDrawerOpened()) {
            mNavigationActivity.closeDrawer();

            mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
                @Override
                public void onNavigate(BaseNavigationDrawerFragment frag) {
                    mNavigationActivity.navigateBack(count);
                }
            }, null);
        } else {
            mNavigationActivity.navigateBack(count);
        }
    }

    protected final void navigateHome() {
        if (mNavigationActivity.isDrawerOpened()) {
            mNavigationActivity.closeDrawer();

            mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
                @Override
                public void onNavigate(BaseNavigationDrawerFragment frag) {
                    mNavigationActivity.navigateBackToFirstLevelFragment();
                }
            }, null);
        } else {
            mNavigationActivity.navigateBackToFirstLevelFragment();
        }
    }

    protected final void setDrawerEnabled(boolean isEnabled) {
        if (mIsDrawerEnabled != isEnabled) {
            mIsDrawerEnabled = isEnabled;
            mNavigationActivity.setDrawerEnabled(mIsDrawerEnabled);
        }
    }

    private void addParam(BaseNavigationDrawerFragment fragment, Parcelable param) {
        if (param != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_PARAM, param);

            fragment.setArguments(bundle);
        }
    }
}
