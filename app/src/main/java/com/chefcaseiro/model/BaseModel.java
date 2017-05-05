package com.chefcaseiro.model;


import java.util.Map;

public abstract class BaseModel {

    public abstract Map<String, Object> toMap();

    private String uid;

    public BaseModel(){

    }

    public BaseModel(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
