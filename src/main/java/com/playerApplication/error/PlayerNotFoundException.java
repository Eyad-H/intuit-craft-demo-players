package com.playerApplication.error;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String msg)
    {
        super(msg);
    }
}
