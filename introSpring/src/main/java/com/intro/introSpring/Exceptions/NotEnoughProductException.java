package com.intro.introSpring.Exceptions;

public class NotEnoughProductException extends RuntimeException{
    public NotEnoughProductException() {
    }

    public NotEnoughProductException(String message) {
        super(message);
    }
}
