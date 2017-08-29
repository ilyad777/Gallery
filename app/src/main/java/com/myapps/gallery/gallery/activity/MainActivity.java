package com.myapps.gallery.gallery.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myapps.gallery.gallery.models.User;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    SharedPreferences sPref;
    static final String UID = "uid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.myapps.gallery.gallery.R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (checkSigning()) {
            Intent intent = new Intent(this, AlbumsActivity.class);
            startActivity(intent);
        } else {
//            getAlbumCovers(mAuth.getCurrentUser());
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        Log.d("debug", "main activity OnCreated finished");
    }

//    private List<Image> getAlbumCovers(FirebaseUser currentUser) {
//        List<Image> covers = new ArrayList<Image>();
//
//        mDatabase.child("0").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                User user = dataSnapshot.getValue(User.class);
//
//                Log.d("DB DEBUG", "User name: " + user.getName() + ", storage " + user.getStorage());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("DB DEBUG", "Failed to read value.", error.toException());
//            }
//        });
//        return covers;
//    }

    private void putUId(String uid) {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(UID, uid);
        ed.commit();
    }

    private boolean checkSigning() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }


}
