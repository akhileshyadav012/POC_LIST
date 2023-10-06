package com.hotelservice.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("Hotel Not Found");
    }
    public NotFoundException(String message){
        super(message);
    }
}
