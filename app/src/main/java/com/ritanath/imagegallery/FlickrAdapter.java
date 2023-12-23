package com.ritanath.imagegallery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ritanath.imagegallery.Model.Photo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


public class FlickrAdapter extends RecyclerView.Adapter<FlickrAdapter.PhotoViewHolder> {

    private List<Photo> photoList = new ArrayList<>();
    @Inject
    MainActivity context;

    public FlickrAdapter( MainActivity context) {

        this.context = context;
    }

    public void setPhotos(List<Photo> photos) {
        Toast.makeText(context, "gfdgsfgs", Toast.LENGTH_SHORT).show();
        photoList.clear();
        photoList.addAll(photos);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        // Use Glide or any image loading library to load image from photo.getUrl_s() into ImageView
        Glide.with(holder.itemView.getContext())
                .load(photo.getUrl_s()) // Assuming getUrl_s() returns the image URL
                .error(R.drawable.ic_launcher_background)// Optional placeholder
                // Optional error placeholder
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        // Initialize views inside the item layout (item_photo.xml)
        ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
           imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
