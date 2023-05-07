package com.example.android_store.network.category;

import com.example.android_store.dtos.category.CategoryCreateDTO;
import com.example.android_store.dtos.category.CategoryItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoriesApi {
    @GET("/api/categories/list")
    public Call<List<CategoryItemDTO>> listCall();

    @POST("/api/categories/create")
    public Call<Void> create(@Body CategoryCreateDTO categoryCreateDTO);

    @DELETE("/api/categories/{id}")
    public Call<Void> delete(@Path("id") int id);
}
