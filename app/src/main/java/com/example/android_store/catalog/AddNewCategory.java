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
        String url = "https://www.collinsdictionary.com/images/full/tree_267376982.jpg";
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(imgLogo);
    }
}