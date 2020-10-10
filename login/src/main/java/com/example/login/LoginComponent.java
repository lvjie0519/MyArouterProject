package com.example.login;

import com.example.component.manager.ComponentManager;
import com.example.component.manager.IComponentLifecycle;
import com.example.login.external.ILoginComponentApi;
import com.google.auto.service.AutoService;

@AutoService(IComponentLifecycle.class)
public class LoginComponent implements IComponentLifecycle {

    @Override
    public void onCreate() {
        ComponentManager.getInstance().registerComponentApi(ILoginComponentApi.KEY, new LoginComponentApiImpl());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
