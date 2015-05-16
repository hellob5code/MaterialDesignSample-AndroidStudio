package com.jp.materialdesignsample.activity.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseNavigationDrawerActivity extends FragmentActivity implements DrawerLayout.DrawerListener {

    private OnNavigateListener mNavigateListener;
    private BaseNavigationDrawerFragment mFragment;

    protected DrawerLayout mDrawerLayout;
    protected FrameLayout mDrawerContentLayout, mDrawerMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutResource());

        mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayoutId());
        mDrawerLayout.setDrawerListener(this);

        mDrawerContentLayout = (FrameLayout) findViewById(getDrawerContentContainerLayoutId());
        mDrawerMenuLayout = (FrameLayout) findViewById(getDrawerMenuContainerLayoutId());

        initMenuFragment();
        initContentFragment();

//        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                String fragments = "";
//                for (Fragment backStackFragment : getSupportFragmentManager().getFragments()) {
//                    if (backStackFragment != null) {
//                        fragments += String.format("[%s]", backStackFragment.getClass().getSimpleName());
//                    }
//                }
//
//                Log.d("NAVIGATION", String.format("BACK STACK COUNT %d %s", getSupportFragmentManager().getBackStackEntryCount(), fragments));
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    public void onDrawerSlide(View view, float v) {

    }

    @Override
    public void onDrawerOpened(View view) {

    }

    @Override
    public void onDrawerClosed(View view) {
        if (mNavigateListener != null) {
            mNavigateListener.onNavigate(mFragment);
            setOnNavigateListener(null, null);
        }
    }

    @Override
    public void onDrawerStateChanged(int i) {
    }

    public void setOnNavigateListener(OnNavigateListener onNavigateListener, BaseNavigationDrawerFragment fragment) {
        mNavigateListener = onNavigateListener;
        mFragment = fragment;
    }

    public void updateToolbarOnFragmentStart(String title) {

    }

    public void updateToolbarOnFragmentStop() {

    }

    private void initMenuFragment() {
        BaseNavigationDrawerFragment menuFragment = getDrawerMenuFragment();

        if (menuFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(getDrawerMenuContainerLayoutId(), menuFragment, menuFragment.getClass().getSimpleName());
            transaction.commit();
        }
    }

    private void initContentFragment() {
        BaseNavigationDrawerFragment contentFragment = getDrawerRootContentFragment();

        if (contentFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_out, android.support.v7.appcompat.R.anim.abc_fade_in);
            transaction.add(getDrawerContentContainerLayoutId(), contentFragment, contentFragment.getClass().getSimpleName());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public final void navigateToFragment(BaseNavigationDrawerFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
        transaction.replace(getDrawerContentContainerLayoutId(), fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public final void navigateToFirstLevelFragment(BaseNavigationDrawerFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
        transaction.replace(getDrawerContentContainerLayoutId(), fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public final void navigateBack() {
        getSupportFragmentManager().popBackStack();
    }

    public final void navigateBack(int count) {
        for (int i = 0; i < count && getSupportFragmentManager().getBackStackEntryCount() - i > 1; i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

    public final void navigateBackToFirstLevelFragment() {
        getSupportFragmentManager().popBackStackImmediate(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public final void setDrawerEnabled(boolean isEnabled) {
        if (isEnabled) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public final boolean getDrawerEnabled() {
        return mDrawerLayout.getDrawerLockMode(mDrawerMenuLayout) == DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    public final void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(mDrawerMenuLayout)) {
            mDrawerLayout.closeDrawer(mDrawerMenuLayout);
        }
    }

    protected abstract int getActivityLayoutResource();

    protected abstract int getDrawerLayoutId();

    protected abstract int getDrawerContentContainerLayoutId();

    protected abstract int getDrawerMenuContainerLayoutId();

    protected abstract BaseNavigationDrawerFragment getDrawerRootContentFragment();

    protected abstract BaseNavigationDrawerFragment getDrawerMenuFragment();
}
