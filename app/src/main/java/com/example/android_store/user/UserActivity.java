package com.example.android_store.user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_store.R;
import com.example.android_store.dtos.user.UserItemDTO;
import com.example.android_store.services.user.UserNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    UserAdapter userAdapter;
    private RecyclerView rcvUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        requestServer();

        rcvUser = findViewById(R.id.rcvUser);
        rcvUser.setHasFixedSize(true);
        rcvUser.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        requestServer();
    }

    private void requestServer() {
        UserNetwork.getInstance().getJsonApi().listCall().enqueue(new Callback<List<UserItemDTO>>() {
            @Override
            public void onResponse(Call<List<UserItemDTO>> call, Response<List<UserItemDTO>> response) {
                List<UserItemDTO> data = response.body();
                userAdapter = new UserAdapter(data);
                rcvUser.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<UserItemDTO>> call, Throwable t) {

            }
        });
    }
}