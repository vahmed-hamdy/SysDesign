package com.interviews.systemdesign.flinkprovider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClusterAlreadyAllocatedException extends IllegalStateException{
    public ClusterAlreadyAllocatedException(String message) {
        super(message);
    }
}
