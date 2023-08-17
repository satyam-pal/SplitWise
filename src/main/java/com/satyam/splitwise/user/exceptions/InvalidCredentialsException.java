package com.satyam.splitwise.user.exceptions;

public class InvalidCredentialsException extends IllegalArgumentException{

    public InvalidCredentialsException(String s) {
        super(s);
    }
}
