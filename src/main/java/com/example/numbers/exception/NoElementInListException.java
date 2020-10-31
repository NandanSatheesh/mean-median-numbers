package com.example.numbers.exception;

public class NoElementInListException extends RuntimeException {
    public NoElementInListException(String message) {
        super(message);
    }
}
