package bootcamp.week4.playlist.repository;

import bootcamp.week4.playlist.domain.Playlist;
import bootcamp.week4.playlist.domain.Track;
import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.core.error.IndexNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void insert(Playlist playlist) {
        if(playlist.getUserId()!=null)
            playlistCollection.insert(playlist.getId(),playlist);
        else
            throw new NullPointerException("userId can not be null");
    }

    public void update(Playlist playlist) {
        playlistCollection.replace(playlist.getId(),playlist);
    }

    public List<Playlist> findAll(String userId, int pageNo, int pageSize) {
        String statement = "Select id, name, description, followersCount, tracks, trackCount, userId from Playlist where userId='" + userId + "' limit " + pageSize + "offset " + pageNo*pageSize;
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }

    public Playlist findById(String id) {
        try{
            GetResult getResult = playlistCollection.get(id);
            return getResult.contentAs(Playlist.class);
        }
        catch (Exception e) {
            return null;
        }
    }

    public void delete(String id) {
        try{
            playlistCollection.remove(id);
        }
        catch (DocumentNotFoundException e) {
            throw new DocumentNotFoundException(e.context());
        }
    }

    public Track getTrack(String id, int index) {
        GetResult getResult = playlistCollection.get(id);
        Playlist playlist = getResult.contentAs(Playlist.class);
        try {
            return playlist.getTracks().get(index);
        }
        catch (Exception e) {
            throw new IndexNotFoundException(e.getMessage());
        }
    }

    public int addTrack(String id, Track track) {
        GetResult getResult = playlistCollection.get(id);
        Playlist playlist = getResult.contentAs(Playlist.class);
        playlist.addTrack(track);
        update(playlist);
        return playlist.getTrackCount();
    }

    public void deleteTrack(String id, int index) {
        GetResult getResult = playlistCollection.get(id);
        Playlist playlist = getResult.contentAs(Playlist.class);
        playlist.removeTrack(index);
        update(playlist);
    }

}
