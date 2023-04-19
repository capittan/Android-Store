package com.example.android_store.user;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_store.R;

public class UserCardViewHolder extends RecyclerView.ViewHolder {
    //private View view;
    public TextView name;

    public UserCardViewHolder(@NonNull View itemView) {
        super(itemView);
        //view = itemView;
        name = itemView.findViewById(R.id.user_name);
    }
}
