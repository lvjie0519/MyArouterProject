package com.example.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

public class MyRouter{

    private Map<String, Class<? extends Activity>> mActivitys = new HashMap<>();
    private Context mContext;
    private static MyRouter instance;

    private MyRouter() {
    }

    public void init(Context context){
        mContext = context.getApplicationContext();

        List<String> listClass = getClassName("com.example.router.util");
        for (String className:listClass) {
            try {
                Class<?> aClass = Class.forName(className);
                if(IMyRouter.class.isAssignableFrom(aClass)){
                    IMyRouter iMyRouter = (IMyRouter) aClass.newInstance();
                    iMyRouter.addActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private List<String> getClassName(String pkgName){
        List<String> classList = new ArrayList<>();

        try {
            DexFile dexFile = new DexFile(mContext.getPackageCodePath());
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()){
                String className = entries.nextElement();
                if(className.contains(pkgName)){
                    classList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return classList;
    }

    public static MyRouter getInstance(){
        if(instance == null){
            synchronized (MyRouter.class){
                if(instance == null){
                    instance = new MyRouter();
                }
            }
        }
        return instance;
    }


    public void addActivity(String key, Class<? extends Activity> clazz) {
        mActivitys.put(key, clazz);
    }

    public void jumpActivity(String key, Bundle bundle){
        Class<? extends Activity> clazz = mActivitys.get(key);
        if(clazz == null){
            return;
        }

        Intent intent = new Intent(mContext, clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }
}
