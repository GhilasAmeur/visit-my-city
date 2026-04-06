package com.example.backend.exceptions;

public class AuthentificationNotFoundException extends RuntimeException {
    public AuthentificationNotFoundException(String message) {
        super(message);
    }
}
