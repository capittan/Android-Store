package com.example.android_store.dtos.category;

public class CategoryCreateDTO {
    private String name;
    private String imagebase64;
    private int priority;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(String imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
