package com.myapps.gallery.gallery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myapps.gallery.gallery.R;
import com.myapps.gallery.gallery.models.Image;

import java.util.List;

/**
 * Created by Xozain on 22.08.2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    List<Image> albumCovers;
    Context context;

    public AlbumsAdapter(List<Image> albumCovers, Context context) {
        this.albumCovers = albumCovers;
        this.context = context;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_card, parent, false);
        return new AlbumViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Image cover = albumCovers.get(position);
        ImageView imageView = holder.albumImage;
        holder.albumName.setText(cover.getName());
        Glide.with(context)
                .load(cover.getUrl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark_disabled)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return albumCovers.size();
    }
}
