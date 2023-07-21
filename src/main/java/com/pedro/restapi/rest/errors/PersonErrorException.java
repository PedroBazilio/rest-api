package com.pedro.restapi.rest.errors;

public class PersonErrorException extends Exception{

    public PersonErrorException(String errorMessage) {
        super(errorMessage);
    }
}
