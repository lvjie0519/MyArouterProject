package com.example.login.external;


import com.example.component.manager.IComponentApi;
import com.example.login.external.bean.UserInfo;

public interface ILoginComponentApi extends IComponentApi{

    String KEY = "com.example.login.external.ILoginComponentApi";


    /**
     * 获取用户信息
     * @return
     */
     UserInfo getUserInfo();

}
