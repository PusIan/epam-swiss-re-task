package com.swiss.exceptions;

public class CeoNotFoundException extends RuntimeException {
    public CeoNotFoundException() {
        super("Ceo not found");
    }
}
