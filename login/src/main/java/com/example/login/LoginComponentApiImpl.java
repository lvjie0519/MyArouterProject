package com.example.login;

import com.example.login.external.ILoginComponentApi;
import com.example.login.external.bean.UserInfo;

public class LoginComponentApiImpl implements  ILoginComponentApi{
    @Override
    public UserInfo getUserInfo() {
        return new UserInfo("zhangsan", "123123");
    }
}
