package com.example.demo.exceptions;

public class ValidationException extends RuntimeException {
    private String msg;

    public ValidationException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}