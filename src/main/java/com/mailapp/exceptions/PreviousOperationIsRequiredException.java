package com.mailapp.exceptions;

public class PreviousOperationIsRequiredException extends RuntimeException {

    public PreviousOperationIsRequiredException(String message) {
        super(message);
    }
}
