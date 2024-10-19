package bootcamp.week4.playlist.config;

import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        try {
            couchbaseCluster.query("CREATE INDEX playlistId ON `Playlist`(Playlist.id);");
            couchbaseCluster.query("CREATE INDEX playlistUserId ON `Playlist`(Playlist.userId);");
            couchbaseCluster.query("CREATE INDEX playlistTracks ON `Playlist`(DISTINCT ARRAY `t`.`name` FOR t in `track` END);");
        }
        catch (Exception e){

        }
    }
}
