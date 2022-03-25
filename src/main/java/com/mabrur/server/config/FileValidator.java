package com.mabrur.server.config;

import java.lang.annotation.Target;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        

        if (value.getOriginalFilename().isEmpty()) {
            return false;
        }

        return true;
    }

}
