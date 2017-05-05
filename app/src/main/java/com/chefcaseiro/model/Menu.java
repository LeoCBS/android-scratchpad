package com.chefcaseiro.model;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.author;

public class Menu extends  BaseModel{

    private String uid;
    private String name;

    public Menu(){
        super();
    }

    public Menu(String uid, String name) {
        super(uid);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", this.name);
        return result;
    }
}
