package com.example.component.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class ComponentManager {

    private static volatile ComponentManager instance;

    private List<IComponentLifecycle> mComponents = new ArrayList<>();
    private Map<String, IComponentApi> mComponentApiMap = new ConcurrentHashMap<>();

    private ComponentManager() {
    }

    public static ComponentManager getInstance(){

        if(instance == null){
            synchronized (ComponentManager.class){
                if(instance == null){
                    instance = new ComponentManager();
                }
            }
        }
        return instance;
    }

    /**
     * 收集注册模块，并分别执行onCreate方法
     */
    public void componentOnCreate() {
        mComponents = new ArrayList<>(getComponents());
        for (IComponentLifecycle component : mComponents) {
            try {
                component.onCreate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<IComponentLifecycle> getComponents() {
        List<IComponentLifecycle> tempComponents = new ArrayList<>();
        ServiceLoader<IComponentLifecycle> loader = ServiceLoader.load(IComponentLifecycle.class);
        Iterator<IComponentLifecycle> iterator = loader.iterator();
        while (iterator.hasNext()) {
            IComponentLifecycle api = iterator.next();
            if(api == null) {
                continue;
            }
            tempComponents.add(api);
        }
        return tempComponents;
    }

    public <T extends IComponentApi> void registerComponentApi(String key, T clazz) {
        mComponentApiMap.put(key, clazz);
    }

    public <T extends IComponentApi> T getComponentApi(String key) {
        return (T) mComponentApiMap.get(key);
    }

}
