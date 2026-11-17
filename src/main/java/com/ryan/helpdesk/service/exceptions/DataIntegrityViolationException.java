package com.ryan.helpdesk.service.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String message){
        super(message);
    }
}
