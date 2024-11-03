package com.swiss.exceptions;

public class WrongNumberOfFieldsInCsvException extends RuntimeException {
    public WrongNumberOfFieldsInCsvException(int numberOfFields) {
        super("Wrong number of fields in CSV " + numberOfFields);
    }
}
