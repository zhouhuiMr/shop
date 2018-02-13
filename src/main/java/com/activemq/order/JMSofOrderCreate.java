package com.activemq.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.activemq.activeMQProducerAbstract;

@Component
public class JMSofOrderCreate extends activeMQProducerAbstract{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public JMSofOrderCreate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void MsgProducerByQueue(String destination, String msg) {
		this.jmsTemplate.convertAndSend(destination, msg);
	}
}
