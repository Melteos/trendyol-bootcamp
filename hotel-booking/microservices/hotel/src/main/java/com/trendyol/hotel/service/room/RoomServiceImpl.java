package com.trendyol.hotel.service.room;

import com.trendyol.common.exceptions.base.RestResourceNotFoundException;
import com.trendyol.common.model.BookingDTO;
import com.trendyol.common.saga.event.BookingCanceledEvent;
import com.trendyol.common.saga.event.BookingDoneEvent;
import com.trendyol.common.utils.UUIDGenerator;
import com.trendyol.hotel.domain.Room;
import com.trendyol.hotel.domain.RoomBooking;
import com.trendyol.hotel.repository.room.RoomRepository;
import com.trendyol.hotel.repository.roomBooking.RoomBookingRepository;
import com.trendyol.hotel.saga.publisher.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;
	private final RoomBookingRepository roomBookingRepository;
	private final EventPublisher eventPublisher;

	@Override
	public void updateRoom(BookingDTO bookingDTO) {
		checkRoomAvailability(bookingDTO);
	}

	@Override
	public Room findById(String id) {
		return roomRepository.findById(id).orElseThrow(RestResourceNotFoundException::new);
	}

	private void checkRoomAvailability(BookingDTO bookingDTO) {
		Room room = findById(bookingDTO.getRoomId());
		Optional<List<RoomBooking>> roomBookingOpt = roomBookingRepository.findByRoomId(room.getId());

		if(roomBookingOpt.isEmpty()){
			BookingDoneEvent event = BookingDoneEvent.builder().bookingDTO(bookingDTO).transactionId(UUIDGenerator.getUUID()).build();
			eventPublisher.publishBookingDoneEvent(event);
		}
		BookingCanceledEvent bookingCanceledEvent = BookingCanceledEvent.builder().bookingDTO(bookingDTO).transactionId(UUIDGenerator.getUUID()).build();
		eventPublisher.publishBookingCanceledEvent(bookingCanceledEvent);
	}
}
