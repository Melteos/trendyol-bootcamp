package com.trendyol.hotel.saga.publisher;

import com.trendyol.common.constants.AmqpConstants;
import com.trendyol.common.converter.Converter;
import com.trendyol.common.saga.event.BookingCanceledEvent;
import com.trendyol.common.saga.event.BookingDoneEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class EventPublisher {

	private final Converter converter;
	private final RabbitTemplate rabbitTemplate;

	public void publishBookingCanceledEvent(BookingCanceledEvent event) {
		this.rabbitTemplate.convertAndSend(AmqpConstants.EXCHANGE_BOOKING_CANCELED, AmqpConstants.ROUTING_KEY_BOOKING_CANCELED, converter.toJSON(event));
	}

	public void publishBookingDoneEvent(BookingDoneEvent event) {
		this.rabbitTemplate.convertAndSend(AmqpConstants.EXCHANGE_BOOKING_DONE, converter.toJSON(event));
	}
}
