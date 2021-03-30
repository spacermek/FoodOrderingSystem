package com.intro.introSpring.Exceptions;

public class NotDefinedAddress extends RuntimeException{
    public NotDefinedAddress() {
    }

    public NotDefinedAddress(String message) {
        super(message);
    }
}
