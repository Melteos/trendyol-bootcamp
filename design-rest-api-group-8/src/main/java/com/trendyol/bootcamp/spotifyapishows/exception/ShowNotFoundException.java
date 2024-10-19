package com.trendyol.bootcamp.spotifyapishows.exception;

public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(){
        super("There is no show with that id.");
    }
}
