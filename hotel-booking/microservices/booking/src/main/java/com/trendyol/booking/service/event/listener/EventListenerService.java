package com.trendyol.booking.service.event.listener;

import com.trendyol.booking.service.booking.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EventListenerService {

	@Autowired
	BookingService bookingService;

	@RabbitListener (queues = "queue-booking")
	public void processBookingCreatedEvent(@Payload String data) {
		System.out.println("Event consumed");
		bookingService.createBooking();
	}
}
