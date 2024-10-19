package com.trendyol.booking.service.booking;

import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

	@Override
	public void createBooking() {
		System.out.println("Now,booking is creating");
	}
}
