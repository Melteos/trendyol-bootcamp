package bootcamp.week4.playlist.service;

import bootcamp.week4.playlist.domain.Playlist;
import bootcamp.week4.playlist.domain.Track;
import bootcamp.week4.playlist.repository.PlaylistRepository;
import com.couchbase.client.core.error.DatasetNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void createPlaylist(Playlist playlist) {
        playlistRepository.insert(playlist);
    }

    public List<Playlist> findAllPlaylistByUserId(String userId, int pageNo, int pageSize) {
        List<Playlist> playlists = playlistRepository.findAll(userId, pageNo, pageSize);
        if(playlists == null)
            throw new IllegalArgumentException(); //should be custom exception about userid not found
        return playlists;
    }

    public Playlist findPlaylist(String id) {
        return playlistRepository.findById(id);
    }

    public void deletePlaylist(String id) {
        playlistRepository.delete(id);
    }

    public Track getTrack(String id, int index) {
        return playlistRepository.getTrack(id, index);
    }

    public int addTrack(String id, Track track) {
        return playlistRepository.addTrack(id,track); //does not satisfy single responsibility
    }

    public void deleteTrack(String id, int index) {
        playlistRepository.deleteTrack(id,index);
    }

}
