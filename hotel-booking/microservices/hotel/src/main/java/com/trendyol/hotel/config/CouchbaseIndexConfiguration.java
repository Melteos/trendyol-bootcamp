package com.trendyol.hotel.config;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import com.couchbase.client.java.manager.query.CreateQueryIndexOptions;
import com.couchbase.client.java.manager.query.QueryIndexManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;
    private final CouchbaseProperties couchbaseProperties;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster, CouchbaseProperties couchbaseProperties) {
        this.couchbaseCluster = couchbaseCluster;
        this.couchbaseProperties = couchbaseProperties;
    }

    @Bean
    public void createIndexes() {
        /*
        couchbaseCluster.query("CREATE INDEX hotelId ON `hotel`(hotel.id);");
        couchbaseCluster.query("CREATE INDEX hotelRoomId ON `hotel`(hotel.roomId);");
        couchbaseCluster.query("CREATE INDEX hotelLocation ON `hotel`(DISTINCT ARRAY `l`.`city` FOR l in `location` END);");
        couchbaseCluster.query("CREATE INDEX hotelRooms ON `hotel`(DISTINCT ARRAY `r`.`roomNumber` FOR r in `room` END);");

         */
        QueryIndexManager indexManager = couchbaseCluster.queryIndexes();

        indexManager.createPrimaryIndex(couchbaseProperties.getBucketName(),
                CreatePrimaryQueryIndexOptions.createPrimaryQueryIndexOptions().ignoreIfExists(Boolean.TRUE));
    }
}
