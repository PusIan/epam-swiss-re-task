package com.swiss.exceptions;

public class MultipleCeoAreNotAllowedException extends RuntimeException {
    public MultipleCeoAreNotAllowedException() {
        super("Multiple CEO are not allowed");
    }
}
