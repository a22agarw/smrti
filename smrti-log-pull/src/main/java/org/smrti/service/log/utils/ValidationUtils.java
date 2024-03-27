package org.smrti.service.log.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationUtils {

    public static ResponseEntity<Object> getValidationError(BindingResult bindingResult) {
        // Extract field validation errors and return a custom response
        JsonObject fieldErrors = ValidationUtils.getFieldErrors(bindingResult);
        JsonObject resultMessage = ResponseUtils.error("Validation error(s)", fieldErrors);
        return new ResponseEntity<>(new Gson().fromJson(resultMessage, Object.class),
                HttpStatus.BAD_REQUEST);
    }

    private static JsonObject getFieldErrors(BindingResult bindingResult) {
        JsonObject fieldErrors = new JsonObject();

        for (FieldError error : bindingResult.getFieldErrors()) {
            // Customize the field error representation here
            fieldErrors.addProperty(error.getField(), error.getDefaultMessage());
        }

        return fieldErrors;
    }
}
