package com.trendyol.hotel.repository.roomBooking;

import com.trendyol.common.model.BookingDTO;
import com.trendyol.hotel.domain.RoomBooking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomBookingRepository {

	public Optional<List<RoomBooking>> findByRoomId(String id);

	public void createRoomBooking(BookingDTO bookingDTO);

}
