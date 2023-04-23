package com.example.android_store;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_store.application.HomeApplication;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        Glide.with(HomeApplication.getAppContext())
                .load("https://www.iconpacks.net/icons/2/free-store-icon-2017-thumb.png")
                .apply(new RequestOptions().override(600))
                .into(imgLogo);
    }
}