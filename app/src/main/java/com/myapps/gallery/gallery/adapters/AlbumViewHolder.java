package com.myapps.gallery.gallery.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapps.gallery.gallery.R;
import com.myapps.gallery.gallery.activity.ImagesActivity;
import com.myapps.gallery.gallery.activity.MainActivity;
import com.myapps.gallery.gallery.models.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xozain on 22.08.2017.
 */
public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.album_name)
    TextView albumName;
    @BindView(R.id.album_photo)
    ImageView albumImage;

    AlbumViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION) {
            Intent intent = new Intent(view.getContext(), ImagesActivity.class);
            intent.putExtra(ImagesActivity.IMAGES, position);
            view.getContext().startActivity(intent);
        }
    }
}

