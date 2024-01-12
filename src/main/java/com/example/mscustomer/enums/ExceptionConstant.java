package com.example.mscustomer.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ExceptionConstant {
    CUSTOMER_NOT_FOUND(1001,"Can not find customer with given id"),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(400,"argument type not valid");

    private int code;
    private String message;

    ExceptionConstant() {
    }

    ExceptionConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }


    }

