package com.hackathon.StaffInformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StaffNotFoundException extends ResponseStatusException {
    public StaffNotFoundException(HttpStatus status, String exception)
    {
        super(status, exception);
    }
}
