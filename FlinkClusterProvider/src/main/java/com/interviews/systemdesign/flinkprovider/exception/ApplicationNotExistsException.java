package com.interviews.systemdesign.flinkprovider.exception;

public class ApplicationNotExistsException extends RuntimeException {
    public ApplicationNotExistsException(String message) {
        super(message);
    }
}
