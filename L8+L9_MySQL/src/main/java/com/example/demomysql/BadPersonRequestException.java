package com.example.demomysql;

public class BadPersonRequestException extends Exception{

    public BadPersonRequestException(String message){
        super(message);
    }
}
