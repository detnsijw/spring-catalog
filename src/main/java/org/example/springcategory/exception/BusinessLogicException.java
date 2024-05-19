package org.example.springcategory.exception;

public class BusinessLogicException extends RuntimeException{
    public BusinessLogicException(String message) {
        super(message);
    }
}