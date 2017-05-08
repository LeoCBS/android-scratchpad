package com.chefcaseiro.helper;

import com.chefcaseiro.model.BaseModel;
import com.chefcaseiro.model.Menu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;

/**
 * Created by Oclemy on 6/21/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<String> menus=new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE
    public Boolean save(BaseModel basemodel)
    {
        if(basemodel==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("menus").push().setValue(basemodel);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

    //READ
    public ArrayList<String> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return menus;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        menus.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            String name=ds.getValue(Menu.class).getName();
            menus.add(name);
        }
    }
}