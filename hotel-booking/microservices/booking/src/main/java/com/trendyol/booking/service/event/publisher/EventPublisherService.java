package com.trendyol.booking.service.event.publisher;

import com.trendyol.booking.constants.AmqpConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void publishBookingCreatedEvent(String id) {
		System.out.println("Event published");
		rabbitTemplate.convertAndSend(AmqpConstants.EXCHANGE_BOOKING, AmqpConstants.ROUTING_KEY_BOOKING, id);
	}
}
