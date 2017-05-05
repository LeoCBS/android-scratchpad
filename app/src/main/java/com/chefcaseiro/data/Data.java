package com.chefcaseiro.data;

import com.chefcaseiro.model.BaseModel;
import com.chefcaseiro.model.Menu;

import java.util.List;

/**
 * Represent data methods
 */

public interface Data {

    public void save(BaseModel menu,String collectionName);
    public List<BaseModel> load(String collectionName, String orderField);
}
