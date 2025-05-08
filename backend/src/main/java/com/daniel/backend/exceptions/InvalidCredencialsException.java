package com.daniel.backend.exceptions;

public class InvalidCredencialsException extends RuntimeException{
    public InvalidCredencialsException(String message) {
        super(message);
    }
}
