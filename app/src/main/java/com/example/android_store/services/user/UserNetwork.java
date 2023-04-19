package com.example.android_store.services.user;

import com.example.android_store.constans.Urls;
import com.example.android_store.network.user.UserApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserNetwork {
    private static UserNetwork mInstance;
    private static final String BASE_URL = Urls.JSON_PLACEHOLDER;
    private Retrofit mRetrofit;

    private UserNetwork() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static UserNetwork getInstance() {
        if (mInstance == null)
            mInstance = new UserNetwork();
        return mInstance;
    }

    public UserApi getJsonApi() {
        return mRetrofit.create(UserApi.class);
    }
}