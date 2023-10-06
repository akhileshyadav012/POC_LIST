package com.ratingservice.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("Ratings Not Found");
    }
    public NotFoundException(String message){
        super(message);
    }
}
