package com.pedro.restapi.rest.errors;

public class PersonErrorException extends RuntimeException{

    public PersonErrorException(String errorMessage) {
        super(errorMessage);
    }
}
