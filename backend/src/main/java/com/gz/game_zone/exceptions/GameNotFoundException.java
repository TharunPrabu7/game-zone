package com.gz.game_zone.exceptions;

public class GameNotFoundException extends RuntimeException{
    private static final long serialVersionUid = 1;

    public GameNotFoundException(String message) {
        super(message);
    }
}
