package com.pedro.restapi.rest.errors;

public class DepartmentErrorException extends Exception {

    public DepartmentErrorException(String errorMessage) {
        super(errorMessage);
    }
}
