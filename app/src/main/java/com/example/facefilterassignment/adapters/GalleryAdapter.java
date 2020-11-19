package com.example.facefilterassignment.adapters;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facefilterassignment.R;

import java.io.File;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    Context context;
    String[] files;
    String path;

    public GalleryAdapter(Context context, String[] files,String path) {
        this.context = context;
        this.files = files;
        this.path = path;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        File imgFile;
        String imgPath = files[position];
        imgFile = new File(path + "/" + imgPath);
        Uri path2 = Uri.fromFile(imgFile);

        Glide.with(context)
                .load(path2)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gallery_img);
        }
    }
}
