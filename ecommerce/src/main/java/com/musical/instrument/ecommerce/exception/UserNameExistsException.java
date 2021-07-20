package com.musical.instrument.ecommerce.exception;

public class UserNameExistsException extends Exception{
    private static final long serialVersionUID = 1L;
    public UserNameExistsException(String message){super(message);}
}
