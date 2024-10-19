package com.trendyol.hotel.service.roomBooking;

import com.trendyol.common.model.BookingDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoomBookingService {

	public void createRoomBooking(BookingDTO bookingDTO);

}
