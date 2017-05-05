package com.chefcaseiro.data;


import android.util.Log;

import com.chefcaseiro.model.BaseModel;
import com.chefcaseiro.model.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseData implements Data {

    private DatabaseReference mDatabase;

    public FirebaseData(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void save(BaseModel model, String collectionName) {
        String key = mDatabase.child(collectionName).push().getKey();
        model.setUid(key);
        Map<String, Object> postValues = model.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + collectionName+ "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    @Override
    public List<BaseModel> load(String colletionName, String orderField) {
        return null;
    }
}
