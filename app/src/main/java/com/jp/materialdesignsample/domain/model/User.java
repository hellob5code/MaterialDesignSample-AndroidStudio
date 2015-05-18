package com.jp.materialdesignsample.domain.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "user")
public class User extends Model implements Parcelable {
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Column(name = KEY_USER_ID)
    private String mUserId;

    @Column(name = KEY_USERNAME)
    private String mUsername;

    @Column(name = KEY_PASSWORD)
    private String mPassword;

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();

        bundle.putString(KEY_USER_ID, mUserId);
        bundle.putString(KEY_USERNAME, mUsername);
        bundle.putString(KEY_PASSWORD, mPassword);

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();

            User user = new User();
            user.setUserId(bundle.getString(KEY_USER_ID));
            user.setUsername(bundle.getString(KEY_USERNAME));
            user.setPassword(bundle.getString(KEY_PASSWORD));

            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
