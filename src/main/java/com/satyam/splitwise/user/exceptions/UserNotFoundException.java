package com.satyam.splitwise.user.exceptions;

public class UserNotFoundException extends IllegalArgumentException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
