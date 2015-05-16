package com.jp.materialdesignsample.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by tuu.phung on 12/05/2015.
 */
public class DimensionUtils {
    public static float convertDpToPixel(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float convertSpToPixel(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
