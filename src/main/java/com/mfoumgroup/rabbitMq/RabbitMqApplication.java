package com.mfoumgroup.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class RabbitMqApplication {
	
	private Logger log = LoggerFactory.getLogger(RabbitMqApplication.class);
	
	private final String topicExchangeName = "spring-amqp-exchange";
	private final String queueName = "q1";
	private final String routingKey = "q1";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(topicExchangeName);
	}
	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}

}
