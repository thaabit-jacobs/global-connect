package com.global.connect.exception;

public class PersonNotFoundException extends Exception{

    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(Throwable cause) {
        super(cause);
    }
}
