package com.example.newlibrary.service.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super("Could not find the book in the library: " + id);
    }
}
