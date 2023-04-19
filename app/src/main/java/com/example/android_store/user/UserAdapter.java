package com.example.android_store.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_store.R;
import com.example.android_store.dtos.user.UserItemDTO;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserCardViewHolder> {
    private List<UserItemDTO> list;

    public UserAdapter(List<UserItemDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);
        return new UserCardViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardViewHolder holder, int position) {
        if (this.list != null && position < this.list.size()) {
            UserItemDTO userItemDTO = this.list.get(position);
            holder.name.setText(userItemDTO.getName());
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
