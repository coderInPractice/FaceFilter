package com.example.facefilterassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.facefilterassignment.adapters.GalleryAdapter;

import java.io.File;
import java.util.ArrayList;

public class ImageGalleryActivity extends AppCompatActivity {

    public static String INNER_MEDIA_PATH = "inner_media_path";
    public static String MEDIA_PATH = "media_path";


    File dir;
    String [] files;

    GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        RecyclerView rv = findViewById(R.id.gallery_rv);
        String innerPath = getIntent().getStringExtra(INNER_MEDIA_PATH);
        String mediaPath = getIntent().getStringExtra(MEDIA_PATH);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        //rv.setLayoutManager(new LinearLayoutManager(this));

        if(requestPermission()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                dir = new File(innerPath);
                files = dir.list();
                galleryAdapter = new GalleryAdapter(this,files,innerPath);
            }else{
                dir = new File(mediaPath);
                files = dir.list();
                galleryAdapter = new GalleryAdapter(this,files,mediaPath);
            }
        }else{
            requestPermission();
        }

        rv.setAdapter(galleryAdapter);
    }

    private boolean requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permission TAG: ","Permission is granted1");
                return true;
            } else {

                Log.v("Permission TAG: ","Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission TAG: ","Permission is granted1");
            return true;
        }
    }

}