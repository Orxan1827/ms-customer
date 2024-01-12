package com.example.mscustomer.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private int code;


    public NotFoundException(){

    }
    public NotFoundException(String message,int code) {
        super(message);
        this.code = code;

    }

}