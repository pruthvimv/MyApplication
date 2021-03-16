package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.URL;

import static com.example.myapplication.MainActivity.EXTRA_URL;

public class DetailActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);

        ImageView imageView = findViewById(R.id.images);
        Glide.with(this).load(url).into(imageView);


    }
}