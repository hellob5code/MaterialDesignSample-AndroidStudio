package com.jp.materialdesignsample.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "user")
public class User extends Model {
    @Column(name = "username")
    public String Username;

    @Column(name = "password")
    public String Password;
}
