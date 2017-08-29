package com.myapps.gallery.gallery.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


import com.myapps.gallery.gallery.R;
import com.myapps.gallery.gallery.adapters.AlbumsAdapter;
import com.myapps.gallery.gallery.models.Image;
import com.myapps.gallery.gallery.models.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private List<Image> covers;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        ButterKnife.bind(this);

        covers = new ArrayList<>();
        covers.add(new Image(Image.getDcImages().get(0).getUrl(), "DC"));
        covers.add(new Image(Image.getMarvelImages().get(0).getUrl(), "Marvel"));

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        AlbumsAdapter albumsAdapter = new AlbumsAdapter(covers, getApplicationContext());

        recyclerView.setAdapter(albumsAdapter);



//        getAlbumCovers(mAuth.getCurrentUser());
//
//        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Gallery/Marvel/f_hulk_avengersmovie.png");
//        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>(){
//            @Override
//            public void onSuccess(Uri uri) {
//                Glide.with(getApplicationContext())
//                        .load(uri)
//                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
//                        .into(imageView);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Log.d("debag", "ВСЕ ПРОПАЛО");
//            }
//        });
//
//
//        Log.d("debag", "типа загрузил");


    }

        private List<Image> getAlbumCovers(FirebaseUser currentUser) {
            List<Image> covers = new ArrayList<Image>();

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
            mDatabase.child("0").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User user = dataSnapshot.getValue(User.class);

                    Log.d("DB DEBUG", "User name: " + user.getName() + ", storage " + user.getStorage());
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("DB DEBUG", "Failed to read value.", error.toException());
                }
            });


            return covers;
        }
        //    @OnClick(R.id.logOutBtn)
//    public void signOut() {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }


}
