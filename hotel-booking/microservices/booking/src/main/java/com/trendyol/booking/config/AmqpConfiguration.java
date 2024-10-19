package com.trendyol.booking.config;

import com.trendyol.booking.constants.AmqpConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AmqpConfiguration {

	@Bean
	@Lazy (false)
	public Exchange bookingExchange() {
		return ExchangeBuilder.directExchange(AmqpConstants.EXCHANGE_BOOKING).durable(true).build();
	}

	@Bean
	@Lazy (false)
	public Queue bookingQueue() {
		return QueueBuilder.durable(AmqpConstants.QUEUE_BOOKING).build();
	}

	@Bean
	@Lazy (false)
	public Binding bookingBinding() {
		return BindingBuilder.bind(bookingQueue()).to(bookingExchange()).with(AmqpConstants.ROUTING_KEY_BOOKING).noargs();
	}

}
