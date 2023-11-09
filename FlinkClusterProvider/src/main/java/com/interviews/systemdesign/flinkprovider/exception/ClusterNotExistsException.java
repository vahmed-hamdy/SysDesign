package com.interviews.systemdesign.flinkprovider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClusterNotExistsException extends RuntimeException{
    public ClusterNotExistsException(String message) {
        super(message);
    }
}
