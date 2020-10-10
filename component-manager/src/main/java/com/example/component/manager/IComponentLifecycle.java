package com.example.component.manager;

public interface IComponentLifecycle {

    void onCreate();
    void onStart();
    void onStop();
    void onDestroy();
}
