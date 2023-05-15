package com.example.android_store.network.account;
import com.example.android_store.dtos.account.LoginDTO;
import com.example.android_store.dtos.account.LoginResponse;
import com.example.android_store.dtos.account.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<Void> register(@Body RegisterDTO registerDTO);
    @POST("/api/account/login")
    public Call<LoginResponse> login(@Body LoginDTO loginDTO);
}