package com.pedro.restapi.rest.errors;

public class TaskErrorException extends Exception {

    public TaskErrorException(String errorMessage) {
        super(errorMessage);
    }
}
