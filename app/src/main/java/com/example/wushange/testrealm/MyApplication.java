package com.example.wushange.testrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wushange on 2016/9/12.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);

        super.onCreate();
    }
}
