package com.example.wushange.testrealm;

import io.realm.RealmObject;

/**
 * Created by wushange on 2016/9/12.
 */
public class User extends RealmObject {
    int id;
    String userName;
    String userGit;
    String userCsdn;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userGit='" + userGit + '\'' +
                ", userCsdn='" + userCsdn + '\'' +
                '}'+"\n";
    }
}
