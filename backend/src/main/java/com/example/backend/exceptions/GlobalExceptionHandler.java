package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCityNotFound(CityNotFoundException e){
        
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleBuildingNotFound(BuildingNotFoundException e){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class, AuthentificationNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEmailNotFound(Exception e){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(CityExistInFavoriesException.class)
        public ResponseEntity<ErrorMessage> handleCityFavoriteException(CityExistInFavoriesException e){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

    @ExceptionHandler(BuildingExistInFavoriesException.class)
    public ResponseEntity<ErrorMessage> handleBuildingFavoriteException(BuildingExistInFavoriesException e){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    }

