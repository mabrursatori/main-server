package com.mabrur.server.doa.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mabrur.server.config.FileNotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class CategoryFormData {

    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @FileNotEmpty
    private MultipartFile image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}
