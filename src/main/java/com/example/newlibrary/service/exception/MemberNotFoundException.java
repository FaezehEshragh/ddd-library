package com.example.newlibrary.service.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String id) {
        super("invalid member id: " + id);
    }
}
