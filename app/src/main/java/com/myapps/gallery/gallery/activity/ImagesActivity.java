package com.myapps.gallery.gallery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.myapps.gallery.gallery.R;
import com.myapps.gallery.gallery.adapters.ImagesAdapter;
import com.myapps.gallery.gallery.models.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesActivity extends AppCompatActivity {
    public static final String IMAGES = "images";

    @BindView(R.id.images_recycler_view)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        Log.d("DEBUG", "creating activity");
        ButterKnife.bind(this);

        List<Image> images = Image.getMarvelImages();
        switch (getIntent().getIntExtra(IMAGES, -1)){
            case 0:
                images = Image.getDcImages();
                break;
            case 1:
                images = Image.getMarvelImages();
                break;
        }

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        ImagesAdapter imagesAdapter = new ImagesAdapter(images, getApplicationContext());
        recyclerView.setAdapter(imagesAdapter);

    }
}
