package com.myapps.gallery.gallery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapps.gallery.gallery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xozain on 22.08.2017.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.album_photo)
    ImageView image;

    ImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

