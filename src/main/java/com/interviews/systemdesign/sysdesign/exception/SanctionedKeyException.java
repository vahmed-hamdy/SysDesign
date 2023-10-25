package com.interviews.systemdesign.sysdesign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SanctionedKeyException extends RuntimeException{
    public SanctionedKeyException(String message) {
        super(message);
    }
}
