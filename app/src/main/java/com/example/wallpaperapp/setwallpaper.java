package com.example.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;



public class setwallpaper extends AppCompatActivity {


    Button dwnldbtn;
    URL url;
    Intent intent;
    ImageView imageView;
    Button set;
    Button sharebt;
    String myurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setwallpaper);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        set = findViewById(R.id.set);
        imageView = findViewById(R.id.finalimage);

        intent = getIntent();
        myurl = intent.getStringExtra("image");


        Glide.with(getApplicationContext()).load(myurl).into(imageView);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sharebt = findViewById(R.id.share);

        sharebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = myurl;
                myIntent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });



        dwnldbtn = findViewById(R.id.download);
        dwnldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage("ImageDownloads",myurl);
            }
        });


    }

void downloadImage(String Filename, String ImageUrl){
try{
    DownloadManager downloadManager =null;
    downloadManager=(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

    Uri downloadiuri = Uri.parse(myurl);
    DownloadManager.Request request = new DownloadManager.Request(downloadiuri);
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
            .setAllowedOverRoaming(false).setTitle(Filename).setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator+Filename+".jpg");

    downloadManager.enqueue(request);
    Toast.makeText(this,"Image downloaded",Toast.LENGTH_SHORT).show();

}
catch (Exception e){
    Toast.makeText(this,"Image downloading failed",Toast.LENGTH_SHORT).show();
}
}
}