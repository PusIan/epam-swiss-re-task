package com.swiss.exceptions;

public class DuplicateEmployeeByIdException extends RuntimeException {
    public DuplicateEmployeeByIdException(int id) {
        super("Multiple employees with id " + id);
    }
}
