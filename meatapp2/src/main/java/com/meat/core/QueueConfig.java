package com.meat.core;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;



@Configuration
public class QueueConfig {

	private static final String ExchangeName="PUSH_NOTIFICATION_EXCHANGE";

	@Resource
	private Environment environment;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =new CachingConnectionFactory();
		connectionFactory.setUsername(environment.getProperty("queue.username"));
		connectionFactory.setPassword(environment.getProperty("queue.password"));
		connectionFactory.setAddresses(environment.getProperty("queue.host"));
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	public TopicExchange setExchange(){
		return new TopicExchange(ExchangeName);
	}

	@Bean
	@Qualifier("scheduled")
	public Queue getScheduledQueue() {
		Queue queue= new Queue(environment.getProperty("queue.scheduled.name"));
		queue.isDurable();
		return queue;
	}
	
	@Bean
	@Qualifier("instant")
	public Queue getInstantQueue() {
		Queue queue= new Queue(environment.getProperty("queue.instant.name"));
		queue.isDurable();
		return queue;
	}


	@Bean
	@Qualifier("scheduledBinding")
	Binding scheduledBinding() {
		return BindingBuilder.bind(getScheduledQueue()).to(setExchange()).with("scheduled");
	}
	
	

	@Bean
	@Qualifier("instantBinding")
	Binding intatntBinding() {
		return BindingBuilder.bind(getInstantQueue()).to(setExchange()).with("instant");
	}
	
//	@PostConstruct
//	public void test(){
//	   	rabbiTemplate.convertAndSend(ExchangeName,"instant",UUID.randomUUID().toString());
//		rabbiTemplate.convertAndSend(ExchangeName,"scheduled",UUID.randomUUID().toString());
//
//	}
}
