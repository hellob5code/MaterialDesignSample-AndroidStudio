package com.jp.materialdesignsample.component;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;

public class DeviceInformation {
    private int mDensity;
    private Point mScreenSize;
    private int mScreenLayout;
    private int mSDKVersion;
    private String mReleaseVersion;

    public DeviceInformation(Context context) {
        loadDeviceInfo(context);
    }

    public int getDensity() {
        return mDensity;
    }

    public String getDensityString() {
        switch (mDensity) {
            case DisplayMetrics.DENSITY_LOW:
                return "LOW";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MEDIUM";
            case DisplayMetrics.DENSITY_HIGH:
                return "HIGH";
            case DisplayMetrics.DENSITY_XHIGH:
                return "X-HIGH";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XX-HIGH";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXX-HIGH";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_280:
                return "280DPI";
            case DisplayMetrics.DENSITY_400:
                return "400DPI";
            case DisplayMetrics.DENSITY_560:
                return "560DPI";
            default:
                return "UNDEFINED";
        }
    }

    public Point getScreenSize() {
        return mScreenSize;
    }

    public String getScreenLayout() {
        switch (mScreenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "SMALL";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "NORMAL";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "LARGE";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return "X-LARGE";
            case Configuration.SCREENLAYOUT_SIZE_MASK:
                return "MASK";
            default:
                return "UNDEFINED";
        }
    }

    public int getSDKVersion() {
        return mSDKVersion;
    }

    public String getReleaseVeresion() {
        return mReleaseVersion;
    }

    private void loadDeviceInfo(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();

        mDensity = displayMetrics.densityDpi;
        mScreenSize = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
        mScreenLayout = configuration.screenLayout;
        mSDKVersion = Build.VERSION.SDK_INT;
        mReleaseVersion = Build.VERSION.RELEASE;
    }
}
