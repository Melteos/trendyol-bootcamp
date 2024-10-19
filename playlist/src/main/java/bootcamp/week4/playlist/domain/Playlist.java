package bootcamp.week4.playlist.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Playlist {

    private String id;
    private String name;
    private String description;
    private int followersCount;
    private List<Track> tracks;
    private int trackCount;
    private String userId;

    public Playlist(String name, String description, String userId) {
        if(userId==null)
            throw new IllegalArgumentException();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.followersCount = 0;
        this.trackCount = 0;
        this.tracks = new ArrayList<Track>();
    }

    public Playlist() {
        this.id = UUID.randomUUID().toString();
        this.name = "default";
        this.description = "default";
        this.userId = null;
        this.followersCount = 0;
        this.trackCount = 0;
        this.tracks = new ArrayList<Track>();
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
        this.trackCount++;
    }

    public void removeTrack(int index) {
        try {
            this.tracks.remove(index-1);
            this.trackCount--;
        }
        catch (Exception e) {
            throw new IndexOutOfBoundsException("track with that index not found");
        }
    }
}
