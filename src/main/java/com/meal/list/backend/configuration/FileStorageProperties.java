package com.meal.list.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDishDir;
    private String uploadIngredientDir;

    public String getUploadIngredientDir() {
        return uploadIngredientDir;
    }

    public void setUploadIngredientDir(String uploadIngredientDir) {
        this.uploadIngredientDir = uploadIngredientDir;
    }



    public String getUploadDishDir() {
        return uploadDishDir;
    }

    public void setUploadDishDir(String uploadDishDir) {
        this.uploadDishDir = uploadDishDir;
    }
}

