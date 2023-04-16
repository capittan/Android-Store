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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        String url = "https://www.collinsdictionary.com/images/full/tree_267376982.jpg";
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(imgLogo);
    }

    public void onClickBtn(View view) {
        Intent intent = new Intent(MainActivity.this, AddNewCategory.class);
        startActivity(intent);
    }
}