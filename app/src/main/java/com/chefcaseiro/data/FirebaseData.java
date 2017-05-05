package com.chefcaseiro.data;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseData implements Data {

    private DatabaseReference mDatabase;

    public FirebaseData(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void save() {


    }

    @Override
    public void load() {


    }
}
