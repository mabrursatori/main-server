package com.mabrur.server.doa.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ItemFormData {

    private Long id;
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Terjemah is required")
    private String contentIndo;
    @NotEmpty(message = "Doa Arabic is required")
    private String contentArabic;
    @NotEmpty(message = "Description is required")
    private String description;
    @Min(1)
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentIndo() {
        return contentIndo;
    }

    public void setContentIndo(String contentIndo) {
        this.contentIndo = contentIndo;
    }

    public String getContentArabic() {
        return contentArabic;
    }

    public void setContentArabic(String contentArabic) {
        this.contentArabic = contentArabic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
