package com.trendyol.hotel.service.room;

import com.trendyol.common.model.BookingDTO;
import com.trendyol.hotel.domain.Room;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

	public void updateRoom(BookingDTO bookingDTO);

	public Room findById(String roomId);

}
