package com.scottslatton.businessapp.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationError {

    private Map<String, String> errors;

    public ValidationError() {
        this.errors = new HashMap<>();
    }

    public void addFieldError(String field, String message) {
        errors.put(field, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}