package com.example.android_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_store.application.HomeApplication;
import com.example.android_store.catalog.CatalogActivity;
import com.example.android_store.catalog.categoryCard.CategoryCardViewHolder;
import com.example.android_store.constans.Urls;
import com.example.android_store.dtos.category.CategoryItemDTO;
import com.example.android_store.network.CategoriesApi;
import com.example.android_store.services.CategoryNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        //String url = Urls.BASE + "/images/2.jpg";
        String url="https://www.collinsdictionary.com/images/full/tree_267376982.jpg";
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(imgLogo);
    }

    public void onClickBtn(View view) {
     Intent intent =new Intent(MainActivity.this, CatalogActivity.class);
     startActivity(intent);
     //finish();
    }
}