package com.userservice.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("User Not Found");
    }
    public NotFoundException(String message){
        super(message);
    }
}
