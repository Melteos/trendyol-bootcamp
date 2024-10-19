package com.trendyol.booking.controller;

import com.trendyol.booking.service.event.publisher.EventPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/booking")
public class BookingController {

	@Autowired
	EventPublisherService eventPublisherService;

	@PostMapping
	public ResponseEntity createBooking(@RequestBody String id) {
		eventPublisherService.publishBookingCreatedEvent(id);
		return ResponseEntity.ok().build();
	}
}
