package com.jp.materialdesignsample.fragment.googleservice;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.OnActivityResultListener;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.adapter.GoogleItemListAdapter;
import com.jp.materialdesignsample.domain.model.GoogleFileItem;
import com.jp.materialdesignsample.service.GoogleServiceManager;
import com.jp.materialdesignsample.service.ServiceConstant;
import com.jp.materialdesignsample.service.base.BaseRestResponse;
import com.jp.materialdesignsample.service.listener.OnServiceResponseListener;
import com.jp.materialdesignsample.service.response.GoogleFileResponse;
import com.jp.materialdesignsample.service.response.SampleGetResponse;

import java.io.IOException;

public class GoogleServiceSampleFragment extends BaseNavigationDrawerFragment
        implements View.OnClickListener, OnActivityResultListener, OnServiceResponseListener<BaseRestResponse>, AdapterView.OnItemClickListener {
    private static final int REQUEST_CODE_PICK_ACCOUNT = 100;
    private static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1001;

    private String mAccessToken;

    private ListView mListView;
    private GoogleFileItem mItem;
    private MediaPlayer mMediaPlayer;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_google_service_sample;
    }

    @Override
    protected void bindView(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.google_music_list);

        rootView.findViewById(R.id.google_music_play_button).setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        authenticate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_music_play_button:
                try {
                    if (mItem != null) {
                        playAudio(mItem.getUrl());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void authenticate() {
        String[] accountTypes = new String[]{"com.google"};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);
        getActivity().startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
    }

    private void getGoogleMusicFiles() {
        String url = "https://www.googleapis.com/drive/v2/files?fields=etag,items(createdDate,downloadUrl,webContentLink,fileSize,id,mimeType,title),kind,nextLink,nextPageToken,selfLink&q=mimeType='audio/mpeg'";
        GoogleServiceManager getServiceManager = new GoogleServiceManager(getActivity(), ServiceConstant.TAG_GET, url, mAccessToken, SampleGetResponse.class, this);
        getServiceManager.executeGet();
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDataSource(url);
        mMediaPlayer.prepare();
        mMediaPlayer.start();
    }

    private void killMediaPlayer() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onHandleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
            if (resultCode == Activity.RESULT_OK) {
                String email = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                getUsername(email);
            }
        }
    }

    public void handleException(final Exception e) {
        // Because this call comes from the AsyncTask, we must ensure that the following
        // code instead executes on the UI thread.
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e instanceof GooglePlayServicesAvailabilityException) {
                    // The Google Play services APK is old, disabled, or not present.
                    // Show a dialog created by Google Play services that allows
                    // the user to update the APK
                    int statusCode = ((GooglePlayServicesAvailabilityException) e)
                            .getConnectionStatusCode();
                    Dialog dialog = GooglePlayServicesUtil.getErrorDialog(statusCode,
                            getActivity(),
                            REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
                    dialog.show();
                } else if (e instanceof UserRecoverableAuthException) {
                    // Unable to authenticate, such as when the user has not yet granted
                    // the app access to the account, but the user can fix this.
                    // Forward the user to an activity in Google Play services.
                    Intent intent = ((UserRecoverableAuthException) e).getIntent();
                    startActivityForResult(intent,
                            REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
                }
            }
        });
    }

    private void getUsername(String email) {
        if (email == null) {
//            pickUserAccount();
        } else {

            String scope = String.format("oauth2:%s %s %s %s %s %s %s",
                    "https://www.googleapis.com/auth/drive",
                    "https://www.googleapis.com/auth/drive.file",
                    "https://www.googleapis.com/auth/drive.readonly",
                    "https://www.googleapis.com/auth/drive.metadata.readonly",
                    "https://www.googleapis.com/auth/drive.appdata",
                    "https://www.googleapis.com/auth/drive.apps.readonly",
                    "https://www.googleapis.com/auth/drive.metadata");
            GetUsernameTask task = new GetUsernameTask(getActivity(), email, scope);
            task.execute();
        }
    }

    @Override
    public void onResponseFailed(String tag) {

    }

    @Override
    public void onResponseSuccess(String tag, BaseRestResponse response) {

    }

    @Override
    public void onParseError(String tag, String responseString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            GoogleFileResponse response = mapper.readValue(responseString, GoogleFileResponse.class);

            GoogleItemListAdapter adapter = new GoogleItemListAdapter(getActivity(), response.getItems());
            mListView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoogleItemListAdapter adapter = (GoogleItemListAdapter) mListView.getAdapter();
        mItem = adapter.getItem(position);
    }

    private class GetUsernameTask extends AsyncTask<Void, Void, Void> {
        Activity mActivity;
        String mScope;
        String mEmail;

        public GetUsernameTask(Activity activity, String name, String scope) {
            this.mActivity = activity;
            this.mScope = scope;
            this.mEmail = name;
        }

        protected String fetchToken() throws IOException {
            try {
                return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
            } catch (GoogleAuthException fatalException) {
                fatalException.printStackTrace();
                handleException(fatalException);
            }
            return null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String token = fetchToken();
                if (token != null) {
                    mAccessToken = token;
                    Log.d("GOOGLE TOKEN", token);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getGoogleMusicFiles();
        }
    }

}
