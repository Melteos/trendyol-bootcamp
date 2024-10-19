package bootcamp.week4.playlist.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Track {

    @NonNull
    private String name;
    @NonNull
    private String length;
    @NonNull
    private String artist;

    public Track(String name, String length, String artist) {
        this.name = name;
        this.length = length;
        this.artist = artist;
    }

    public Track() {
        this.name = "default";
        this.length = "1 s";
        this.artist = "default";
    }
}
