package com.ryan.helpdesk.controller.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ValidationError extends StandardError{
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path, List<FieldMessage> errors) {
        super(timestamp, status, error, message, path);
        this.errors = errors;
    }

    public ValidationError(List<FieldMessage> errors) {
        this.errors = errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }


}


