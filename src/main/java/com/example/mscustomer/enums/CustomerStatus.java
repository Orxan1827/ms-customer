package com.example.mscustomer.enums;

public enum CustomerStatus {
    ACTIVE(1, "ACTIVE"), INACTIVE(0, "INACTIVE"), WAITING_FOR_APPROVE(2, "WAITING_FOR_APPROVE");
    private final int code;
    private final String status;

    CustomerStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }
}
