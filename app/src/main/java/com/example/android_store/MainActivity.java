package com.example.android_store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_store.application.HomeApplication;
import com.example.android_store.catalog.AddNewCategory;
import com.example.android_store.catalog.CatalogActivity;
import com.example.android_store.user.UserActivity;

public class MainActivity extends AppCompatActivity {

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

    public void goToAddNewCategory(View view) {
        Intent intent = new Intent(MainActivity.this, AddNewCategory.class);
        startActivity(intent);
    }
    public void goToCatalogActivity(View view) {
        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
        startActivity(intent);
    }
    public void goToUserActivity(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }
}