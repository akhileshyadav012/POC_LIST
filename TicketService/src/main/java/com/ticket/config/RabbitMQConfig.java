package com.ticket.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.util.ConditionalOnBootstrapEnabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${ticket.queue.name}")
    private String queueName;
    @Value("${ticket.exchange.name}")
    private String queueExchange;
    @Value("${ticket.routing.key}")
    private String queueRouting;

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(queueExchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(queueRouting);
    }

//    ConnectionFactory, RabbitTemplate, RabbitAdmin: Need to configure if we want to pass json value.
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
