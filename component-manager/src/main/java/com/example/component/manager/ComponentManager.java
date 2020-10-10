package com.example.component.manager;

public class ComponentManager {

    private static ComponentManager instance;

    private ComponentManager() {
    }

    public ComponentManager getInstance(){

        if(instance == null){
            synchronized (ComponentManager.class){
                if(instance == null){
                    instance = new ComponentManager();
                }
            }
        }
        return instance;
    }

}
