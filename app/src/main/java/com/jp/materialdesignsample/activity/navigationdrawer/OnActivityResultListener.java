package com.jp.materialdesignsample.activity.navigationdrawer;

import android.content.Intent;

public interface OnActivityResultListener {
    void onHandleActivityResult(int requestCode, int resultCode, Intent data);
}
