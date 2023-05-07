package com.example.android_store.catalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_store.BaseActivity;
import com.example.android_store.R;
import com.example.android_store.catalog.categoryCard.CategoryAdapter;
import com.example.android_store.dtos.category.CategoryItemDTO;
import com.example.android_store.services.category.CategoryNetwork;
import com.example.android_store.utils.CommonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends BaseActivity {
    CategoryAdapter categoryAdapter;
    private RecyclerView rcvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        rcvCategories = findViewById(R.id.rcvCategories);
        rcvCategories.setHasFixedSize(true);
        rcvCategories.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        requestServer();
    }

    private void requestServer() {
        CommonUtils.showLoading();
        CategoryNetwork.getInstance().getJsonApi().listCall().enqueue(new Callback<List<CategoryItemDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                CommonUtils.hideLoading();
                List<CategoryItemDTO> data = response.body();
                categoryAdapter = new CategoryAdapter(data, CatalogActivity.this::onClickDelete, CatalogActivity.this::onClickUpdate);
                rcvCategories.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });
    }

    void onClickDelete(CategoryItemDTO categoryItemDTO) {
        CommonUtils.showLoading();
        CategoryNetwork.getInstance()
                .getJsonApi()
                .delete(categoryItemDTO.getId())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        CommonUtils.hideLoading();
                        Intent intent = new Intent(CatalogActivity.this, CatalogActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
    }

    void onClickUpdate(CategoryItemDTO categoryItemDTO) {
        Intent intent = new Intent(CatalogActivity.this, CategoryUpdateActivity.class);
        Bundle b = new Bundle();
        b.putInt("id", categoryItemDTO.getId());
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}