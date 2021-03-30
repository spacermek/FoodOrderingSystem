package com.intro.introSpring.Exceptions;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String str) {
        super(str);
    }
}
