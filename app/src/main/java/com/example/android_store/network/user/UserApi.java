package com.example.android_store.network.user;

import com.example.android_store.dtos.user.UserItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("/users")
    public Call<List<UserItemDTO>> listCall();
}
