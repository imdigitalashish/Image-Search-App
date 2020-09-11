package com.imdigitalashish.imagesearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.imdigitalashish.imagesearchapp.MainActivity.EXTRA_CREATOR;
import static com.imdigitalashish.imagesearchapp.MainActivity.EXTRA_LIKES;
import static com.imdigitalashish.imagesearchapp.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    BitmapDrawable drawable;
    Bitmap bitmap;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        download = findViewById(R.id.btn_download);
        download.setVisibility(View.GONE);
        final String imageURL = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likes = intent.getIntExtra(EXTRA_LIKES, 0);


        final ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textView = findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);

        Picasso.get().load(imageURL).fit().centerInside().into(imageView);
        textView.setText(creatorName);
        textViewLikes.setText("Likes: " + likes);
        download.setVisibility(View.VISIBLE);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = (BitmapDrawable)imageView.getDrawable();
                bitmap = drawable.getBitmap();

                File sdcard = Environment.getExternalStorageDirectory();
                File directory = new File(sdcard.getAbsolutePath()+"/ImageSearch");
                directory.mkdir();
                FileOutputStream outputStrea = null;
                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(directory, fileName);
                // TODO Add Permission
                try {
                    outputStrea = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStrea);
                    outputStrea.flush();
                    outputStrea.close();
                    // TO show in Gallery
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outFile));
                    sendBroadcast(intent);


                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }

}