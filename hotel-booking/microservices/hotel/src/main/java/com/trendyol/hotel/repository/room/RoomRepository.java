package com.trendyol.hotel.repository.room;

import com.trendyol.hotel.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository {

	public Optional<Room> findById(String id);

}
