package com.melardev.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melardev.models.forms.FileUploadForm;

public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FileUploadForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "", "file.empty", "");
        FileUploadForm f = (FileUploadForm) target;
        if (f.getFile().getSize() == 0) {
            errors.rejectValue("file", "file.empty", "File can not be empty");
        }
    }

}
