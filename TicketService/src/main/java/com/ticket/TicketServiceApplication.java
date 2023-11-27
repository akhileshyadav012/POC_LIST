package com.ticket;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableRabbit
public class TicketServiceApplication implements CommandLineRunner {

	@Value("${ticket.queue.name}")
	private String queueName;
	@Value("${ticket.exchange.name}")
	private String queueExchange;
	@Value("${ticket.routing.key}")
	private String queueRouting;

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(queueName);
//		System.out.println(queueExchange);
//		System.out.println(queueRouting);

	}
}
