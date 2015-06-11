package com.jp.materialdesignsample.component.googleservice.googledrive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.query.Query;

public class GoogleDriveQuery {
    private GoogleApiClient mGoogleApiClient;

    public GoogleDriveQuery(GoogleApiClient client) {
        mGoogleApiClient = client;
    }

    public void queryFiles(ResultCallback<DriveApi.MetadataBufferResult> callback) {
        Query query = new Query.Builder()
                .build();
//        Drive.DriveApi.query(mGoogleApiClient, query)
//                .setResultCallback(callback);

        Drive.DriveApi.getRootFolder(mGoogleApiClient)
                .listChildren(mGoogleApiClient)
                .setResultCallback(callback);
    }
}
