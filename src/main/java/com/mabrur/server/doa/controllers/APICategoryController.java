package com.mabrur.server.doa.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.mabrur.server.doa.dto.ResponseData;
import com.mabrur.server.doa.entities.Category;
import com.mabrur.server.doa.services.CategoryService;
import com.mabrur.server.doa.utils.ImageUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestController
@RequestMapping("/api/doa/category")
public class APICategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@RequestParam("name") String name,
            @RequestParam("image") MultipartFile file) {
        ResponseData<Category> responseData = new ResponseData<>();

        Category category = categoryService.save(file, name);
        if (category != null) {
            responseData.setPayload(category);
            responseData.setStatus(true);
            responseData.setMessages(Arrays.asList("Save category is successfull!"));

            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findOne(@PathVariable("id") Long id) {

        Optional<Category> category = categoryService.findOne(id);
        if (category.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(category.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> update(@RequestParam("name") String name,
            @RequestParam("image") MultipartFile file, @PathVariable("id") Long id) {

        ResponseData<Category> responseData = new ResponseData<>();

        Category category = categoryService.save(file, name, id);
        if (category != null) {
            responseData.setPayload(category);
            responseData.setStatus(true);
            responseData.setMessages(Arrays.asList("Save category is successfull!"));

            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {

        Optional<Category> category = categoryService.findOne(id);

        if (category.isPresent()) {
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<byte[]>(category.get().getImage(), headers, HttpStatus.OK);
        }
        return null;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseData<Object>> handleValidationName(
            MissingServletRequestParameterException e) {

        ResponseData<Object> responseData = new ResponseData<Object>();

        responseData.setStatus(false);
        responseData.setMessages(Arrays.asList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ResponseData<Object>> handleValidationName(
            MissingServletRequestPartException e) {

        ResponseData<Object> responseData = new ResponseData<Object>();

        responseData.setStatus(false);
        responseData.setMessages(Arrays.asList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

    }
}
