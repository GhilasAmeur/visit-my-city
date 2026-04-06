package com.example.backend.exceptions;

public class BuildingExistInFavoriesException extends RuntimeException {
    public BuildingExistInFavoriesException(String message) {
        super(message);
    }
}
