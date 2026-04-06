package com.example.backend.exceptions;
//exception non vérifier c'est pour ça RuntimeException, pas besoin de throw partout
public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String message) {
        super(message);
    }


}
