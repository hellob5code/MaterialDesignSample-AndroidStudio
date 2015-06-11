package com.jp.materialdesignsample.component.googleservice;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.Drive;

public class GoogleServiceClient implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int RESOLVE_CONNECTION_REQUEST_CODE = 1001;

    private Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private OnGoogleConnectedListener mOnConnectedListener;

    private boolean mResolvingError = false;

    public GoogleServiceClient(Context context, Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope scope) {
        mContext = context;

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addApi(api)
                .addScope(scope)
                .addScope(Drive.SCOPE_APPFOLDER)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void handleOnActivityResult(int requestCode, int resultCode) {
        if (requestCode == RESOLVE_CONNECTION_REQUEST_CODE) {
            mResolvingError = false;
            if (resultCode == Activity.RESULT_OK) {
                if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    public GoogleApiClient getApiClient() {
        return mGoogleApiClient;
    }

    public void setOnGoogleConnectedListener(OnGoogleConnectedListener listener) {
        mOnConnectedListener = listener;
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (mOnConnectedListener != null) {
            mOnConnectedListener.onConnected();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            mResolvingError = true;
        } else if (connectionResult.hasResolution()) {
            try {
                mResolvingError = true;
                connectionResult.startResolutionForResult((Activity) mContext, RESOLVE_CONNECTION_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), (Activity) mContext, RESOLVE_CONNECTION_REQUEST_CODE);
            dialog.show();
            mResolvingError = true;
        }
    }
}
