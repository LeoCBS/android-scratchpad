package com.chefcaseiro.model;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Dish {

    private String uid;
    private String name;

    public Dish(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
