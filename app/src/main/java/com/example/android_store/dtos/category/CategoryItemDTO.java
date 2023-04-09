package com.example.android_store.dtos.category;

public class CategoryItemDTO {
    private int id;
    private String name;
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
