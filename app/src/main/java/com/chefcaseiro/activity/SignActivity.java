package com.chefcaseiro.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.chefcaseiro.data.FirebaseData;
import com.chefcaseiro.model.BaseModel;
import com.chefcaseiro.model.Menu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    Button save;
    EditText nameEditText;
    EditText messageEditText;
    ProgressBar progressBar;
    static final String TAG = "Main Acvity";
    ArrayAdapter<String> valuesAdapter;
    ArrayList<String> displayArray;
    ArrayList<String> keysArray;
    ListView listView;
    FirebaseData firebaseData;
    private DatabaseReference mDatabase;
    List<BaseModel> menus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        save = (Button)findViewById(R.id.save);
        nameEditText = (EditText)findViewById(R.id.name);
        messageEditText= (EditText)findViewById(R.id.message);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        listView = (ListView)findViewById(R.id.listView);

        displayArray  = new ArrayList<>();
        keysArray = new ArrayList<>();
        valuesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,displayArray);
        listView.setAdapter(valuesAdapter);

        this.firebaseData = new FirebaseData();
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                String nameString = nameEditText.getText().toString();
                String messageString = messageEditText.getText().toString();
                save(nameString,messageString);
                break;
        }
    }

    private void loadMenus() {
        Query query = this.mDatabase.child("menus").orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    menus.add(postSnapshot.getValue(BaseModel.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });
    }

    private void save(String name,String message){
        Menu menu = new Menu();
        menu.setName(name);
        this.firebaseData.save(menu, "menus");
    }

    private void updateListView(){
        valuesAdapter.notifyDataSetChanged();
        listView.invalidate();
        Log.d(TAG, "Length: " + displayArray.size());
    }


}
