package com.trendyol.hotel.repository.roomBooking;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.trendyol.common.model.BookingDTO;
import com.trendyol.common.utils.UUIDGenerator;
import com.trendyol.hotel.domain.RoomBooking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoomBookingRepositoryImpl implements RoomBookingRepository {

	//private final Cluster couchbaseCluster;
	//private final Collection roomBookingColection;

	@Override
	public Optional<List<RoomBooking>> findByRoomId(String id) {
		return Optional.empty();
	}

	@Override
	public void createRoomBooking(BookingDTO bookingDTO) {
		//roomBookingColection.insert(UUIDGenerator.getUUID(), bookingDTO);
	}
}
