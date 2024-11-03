package com.swiss.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(int managerId) {
        super("Employee with id " + managerId + " does not exist");
    }
}
