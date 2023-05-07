package com.example.android_store.catalog.categoryCard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_store.R;
import com.example.android_store.application.HomeApplication;
import com.example.android_store.constans.Urls;
import com.example.android_store.dtos.category.CategoryItemDTO;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {
    private List<CategoryItemDTO> categoryItemDTOList;
    private final OnCategoryClickListener onClickDelete;
    private final OnCategoryClickListener onClickUpdate;

    public CategoryAdapter(List<CategoryItemDTO> categoryItemDTOList,
                           OnCategoryClickListener onClickDelete,
                           OnCategoryClickListener onClickUpdate) {
        this.categoryItemDTOList = categoryItemDTOList;
        this.onClickDelete = onClickDelete;
        this.onClickUpdate = onClickUpdate;
    }

    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryCardViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCardViewHolder holder, int position) {
        if (categoryItemDTOList != null && position < categoryItemDTOList.size()) {
            CategoryItemDTO categoryItemDTO = categoryItemDTOList.get(position);
            holder.categoryName.setText(categoryItemDTO.getName());
            String url = Urls.BASE + categoryItemDTO.getImage();
            Glide.with(HomeApplication.getAppContext())
                    .load(url)
                    .apply(new RequestOptions().override(600))
                    .into(holder.categoryImage);
            holder.btnCategoryDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickDelete.onButtonClick(categoryItemDTO);
                }
            });
            holder.btnCategoryUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickUpdate.onButtonClick(categoryItemDTO);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categoryItemDTOList.size();
    }
}
