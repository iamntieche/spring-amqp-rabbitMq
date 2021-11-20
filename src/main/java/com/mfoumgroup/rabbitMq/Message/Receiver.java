package com.mfoumgroup.rabbitMq.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	public void receiverMessage(String message) {
		logger.debug("Received < "+message+" > " );
	}
}
