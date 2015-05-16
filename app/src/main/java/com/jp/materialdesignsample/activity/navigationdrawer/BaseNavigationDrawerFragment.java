package com.jp.materialdesignsample.activity.navigationdrawer;

import android.app.Activity;
import android.view.animation.AnimationUtils;

import com.jp.materialdesignsample.fragment.base.BaseFragment;

public abstract class BaseNavigationDrawerFragment extends BaseFragment {
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

    protected final void navigateTo(BaseNavigationDrawerFragment fragment) {
        mNavigationActivity.closeDrawer();
        mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
            @Override
            public void onNavigate(BaseNavigationDrawerFragment frag) {
                mNavigationActivity.navigateToFragment(frag);
            }
        }, fragment);
    }

    protected final void navigateTo(BaseNavigationDrawerFragment fragment, final boolean isFirstLevelFragment) {
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

    }

    protected final void navigateTo(BaseNavigationDrawerFragment fragment, final boolean isFirstLevelFragment, final boolean willRefresh) {
        mNavigationActivity.closeDrawer();

        mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
            @Override
            public void onNavigate(BaseNavigationDrawerFragment frag) {
                navigateTo(frag, isFirstLevelFragment);
                if (willRefresh) {
                    frag.refreshFragment();
                }
            }
        }, fragment);


    }

    protected final void navigateBack() {
        mNavigationActivity.closeDrawer();
        mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
            @Override
            public void onNavigate(BaseNavigationDrawerFragment frag) {
                mNavigationActivity.navigateBack();
            }
        }, null);

    }

    protected final void navigateBack(final int count) {
        mNavigationActivity.closeDrawer();

        mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
            @Override
            public void onNavigate(BaseNavigationDrawerFragment frag) {
                mNavigationActivity.navigateBack(count);
            }
        }, null);
    }

    protected final void navigateHome() {
        mNavigationActivity.closeDrawer();

        mNavigationActivity.setOnNavigateListener(new OnNavigateListener() {
            @Override
            public void onNavigate(BaseNavigationDrawerFragment frag) {
                mNavigationActivity.navigateBackToFirstLevelFragment();
            }
        }, null);
    }

    protected final void setDrawerEnabled(boolean isEnabled) {
        if (mIsDrawerEnabled != isEnabled) {
            mIsDrawerEnabled = isEnabled;
            mNavigationActivity.setDrawerEnabled(mIsDrawerEnabled);
        }
    }
}
