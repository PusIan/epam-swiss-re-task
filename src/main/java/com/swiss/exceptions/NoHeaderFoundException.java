package com.swiss.exceptions;

public class NoHeaderFoundException extends RuntimeException {
    public NoHeaderFoundException() {
        super("No header found");
    }
}
