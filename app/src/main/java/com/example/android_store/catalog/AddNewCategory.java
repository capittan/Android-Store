package com.example.android_store.catalog;



import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_store.R;
import com.example.android_store.application.HomeApplication;

public class AddNewCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        Glide.with(HomeApplication.getAppContext())
                .load("https://cdn-icons-png.flaticon.com/128/6631/6631821.png")
                .apply(new RequestOptions().override(600))
                .into(imgLogo);
    }
}