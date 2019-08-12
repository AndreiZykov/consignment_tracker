package com.pawntracker.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImagesValidation implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.equals(clazz);
    }


    public void validate(Object object, Errors errors) {

        MultipartFile  multipartFile1 = (MultipartFile) object;
        if (multipartFile1 == null) {

            errors.rejectValue("file", "empty", "You should add an image");
        }


    }
}
