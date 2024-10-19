package com.trendyol.hotel.saga.handler;

import com.trendyol.common.constants.AmqpConstants;
import com.trendyol.common.converter.Converter;
import com.trendyol.common.model.BookingDTO;
import com.trendyol.hotel.service.room.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class BookingCreatedHandler {

	private final Converter converter;
	private final RoomService roomService;

	@RabbitListener (queues = AmqpConstants.QUEUE_BOOKING_CREATED_HOTEL)
	public void handleBookingCreatedEvent(@Payload String payload) {
		log.info("Handling a booking created event {}", payload);
		BookingDTO bookingDTO = converter.toObject(payload,BookingDTO.class);
		roomService.updateRoom(bookingDTO);
	}
}
