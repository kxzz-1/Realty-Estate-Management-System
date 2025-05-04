package org.example.finalproject;


public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException() {
    }

    public InvalidLoginException(String message) {
        super(message);
    }
}