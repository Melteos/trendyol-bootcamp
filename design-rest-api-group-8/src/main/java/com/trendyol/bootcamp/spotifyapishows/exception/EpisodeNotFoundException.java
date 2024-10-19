package com.trendyol.bootcamp.spotifyapishows.exception;

public class EpisodeNotFoundException extends RuntimeException {
    public EpisodeNotFoundException() {
        super("There is no episode with that id that belongs to the chosen show.");
    }
}
