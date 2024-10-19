package com.trendyol.hotel.service.roomBooking;

import com.trendyol.common.model.BookingDTO;
import com.trendyol.common.utils.UUIDGenerator;
import com.trendyol.hotel.domain.RoomBooking;
import org.springframework.stereotype.Service;

@Service
public class RookBookingServiceImpl implements RoomBookingService {

	@Override
	public void createRoomBooking(BookingDTO bookingDTO) {
		RoomBooking roomBooking = RoomBooking.builder()
				.id(UUIDGenerator.getUUID())
				.roomId(bookingDTO.getRoomId())
				.startDate(bookingDTO.getStartDate())
				.endDate(bookingDTO.getEndDate())
				.isActive(true)
				.build();

		//TODO: save roomBooking
	}

}
