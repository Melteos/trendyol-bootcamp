package bootcamp.week4.playlist.controller;

import bootcamp.week4.playlist.domain.Playlist;
import bootcamp.week4.playlist.domain.Track;
import bootcamp.week4.playlist.service.PlaylistService;
import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.core.error.IndexNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity createPlaylist(@RequestBody Playlist playlist) {
        try{
            URI location = URI.create(String.format("/playlists/%s", playlist.getId()));
            playlistService.createPlaylist(playlist);
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> findAllPlaylistByUserId(@RequestParam String userId,
                                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        try{
            List<Playlist> playlists = playlistService.findAllPlaylistByUserId(userId, pageNo, pageSize);
            return ResponseEntity.ok(playlists);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findPlaylist(@PathVariable String id){
        Playlist playlist = playlistService.findPlaylist(id);

        if(playlist == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(playlist);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlaylist(@PathVariable String id) {
        try{
            playlistService.deletePlaylist(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/tracks/{index}")
    public ResponseEntity<Track> getTrackOfPlaylist(@PathVariable String id, @PathVariable String index) {
        try {
            Track track = playlistService.getTrack(id, Integer.parseInt(index));
            return ResponseEntity.ok(track);
        }
        catch (IndexNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
        @PostMapping("/{id}/tracks")
    public ResponseEntity addTrack(@PathVariable String id, @RequestBody Track track) {
        try{
            int index = playlistService.addTrack(id, track);
            URI location = URI.create(String.format("/playlists/%s/tracks/%d", id, index));
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/tracks/{index}")
    public ResponseEntity deleteTrack(@PathVariable String id, @PathVariable String index) {
        try{
            playlistService.deleteTrack(id, Integer.parseInt(index));
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
