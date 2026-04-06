package com.example.backend.exceptions;

public class CityExistInFavoriesException extends RuntimeException {
    public CityExistInFavoriesException(String message) {
        super(message);
    }
}
