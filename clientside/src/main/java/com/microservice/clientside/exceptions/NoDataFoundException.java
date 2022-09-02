package com.microservice.clientside.exceptions;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String message){
        super(message);
    }
}
