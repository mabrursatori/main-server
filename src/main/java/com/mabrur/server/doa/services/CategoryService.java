package com.mabrur.server.doa.services;

import java.io.IOException;
import java.util.Optional;

import com.mabrur.server.doa.entities.Category;
import com.mabrur.server.doa.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(MultipartFile file, String name) {

        try {
            Category category = new Category();
            category.setName(name);
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            category.setImage(byteObjects);
            categoryRepo.save(category);

            return category;

        } catch (IOException e) {
            return null;
        }

    }

    public Category save(MultipartFile file, String name, Long id) {

        try {
            Optional<Category> categoryResult = categoryRepo.findById(id);
            if(!categoryResult.isPresent()) return null;

            Category category = categoryResult.get();

            category.setName(name);
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            category.setImage(byteObjects);
            categoryRepo.save(category);

            return category;

        } catch (IOException e) {
            return null;
        }

    }

    public Category save(Category category) {
        if (category.getId() != null) {
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            currentCategory.setImage(category.getImage());
            category = currentCategory;
        }
        return categoryRepo.save(category);
    }

    public Optional<Category> findOne(Long id) {
        return categoryRepo.findById(id);
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

}
