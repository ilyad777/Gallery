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

public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    List<Image> images;
    Context context;

    public ImagesAdapter(List<Image> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Image image = images.get(position);
        ImageView imageView = holder.image;

        Glide.with(context)
                .load(image.getUrl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark_disabled)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
