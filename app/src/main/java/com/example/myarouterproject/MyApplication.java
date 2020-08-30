package com.example.myarouterproject;

import android.app.Application;

import com.example.router.MyRouter;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyRouter.getInstance().init(getApplicationContext());
    }
}
