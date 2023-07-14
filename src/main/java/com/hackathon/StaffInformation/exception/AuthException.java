package com.hackathon.StaffInformation.exception;

import javax.naming.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String exception)
    {
        super(exception);
    }
}
