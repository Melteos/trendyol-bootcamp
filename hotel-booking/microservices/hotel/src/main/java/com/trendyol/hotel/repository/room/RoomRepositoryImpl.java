package com.trendyol.hotel.repository.room;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.trendyol.hotel.domain.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {

	//private final Cluster couchbaseCluster;
	//private final Collection roomColection;

	@Override
	public Optional<Room> findById(String id) {
		//GetResult getResult = roomColection.get(id);
		//return Optional.of(getResult.contentAs(Room.class));
		return Optional.empty();
	}
}
