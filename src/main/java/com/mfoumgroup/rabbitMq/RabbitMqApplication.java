package com.mfoumgroup.rabbitMq;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mfoumgroup.rabbitMq.Message.Receiver;
import com.mfoumgroup.rabbitMq.utils.RabbitMqUtils;



@SpringBootApplication
public class RabbitMqApplication {
	
	private Logger log = LoggerFactory.getLogger(RabbitMqApplication.class);
	
	
	@Bean
	Queue queue() {
		return new Queue(RabbitMqUtils.queueName, false);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(RabbitMqUtils.topicExchangeName);
	}
	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqUtils.routingKey);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(RabbitMqUtils.queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiverMessage");
	}
	public static void main(String[] args) throws InterruptedException{
		SpringApplication.run(RabbitMqApplication.class, args).close();
	}

}
