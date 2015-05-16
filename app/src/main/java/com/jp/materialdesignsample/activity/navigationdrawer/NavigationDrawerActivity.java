package com.jp.materialdesignsample.activity.navigationdrawer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.fragment.NavigationDrawerMenuFragment;

public class NavigationDrawerActivity extends BaseNavigationDrawerActivity {
    private static final int TITLE_ANIMATION_DURATION = 200;

    private TextView mTitleTextView;

    @Override
    protected int getActivityLayoutResource() {
        return R.layout.activity_navigation_drawer;
    }

    @Override
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getDrawerContentContainerLayoutId() {
        return R.id.drawer_content_frame;
    }

    @Override
    protected int getDrawerMenuContainerLayoutId() {
        return R.id.drawer_menu_frame;
    }

    @Override
    protected BaseNavigationDrawerFragment getDrawerRootContentFragment() {
        return null;
    }

    @Override
    protected BaseNavigationDrawerFragment getDrawerMenuFragment() {
        return new NavigationDrawerMenuFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        mTitleTextView = (TextView) findViewById(R.id.toolbar_title_text);
    }

    @Override
    public void updateToolbarOnFragmentStart(String title) {
        super.updateToolbarOnFragmentStart(title);

        updateToolbarTitle(title);
    }


    @Override
    public void updateToolbarOnFragmentStop() {
        super.updateToolbarOnFragmentStop();
    }

    private void updateToolbarTitle(final String title) {
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mTitleTextView, View.ALPHA, 0.0f);
        fadeIn.setDuration(TITLE_ANIMATION_DURATION);
        fadeIn.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mTitleTextView.setText(title);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mTitleTextView, View.ALPHA, 1.0f);
        fadeIn.setDuration(TITLE_ANIMATION_DURATION);

        animSet.play(fadeIn).before(fadeOut);
        animSet.start();
    }
}
