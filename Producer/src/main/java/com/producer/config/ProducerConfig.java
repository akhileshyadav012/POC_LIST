package com.producer.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

@Configuration
public class ProducerConfig {
    @Value("${one.queue.name}")
    private String oneQueue;
    @Value("${one.exchange.name}")
    private String oneExchange;
    @Value("${one.routing.key}")
    private String oneRoutingKey;

    @Value("${two.queue.name}")
    private String twoQueue;
    @Value("${two.routing.key}")
    private String twoRoutingKey;

    @Value("${mobile.queue}")
    private String mobileQueue;
    @Value("${mobile.exchange}")
    private String mobileExchange;
    @Value("${mobile.routing.key}")
    private String mobileRoutingKey;

    @Bean
    public Queue queueOne(){
        return new Queue(oneQueue);
    }
    @Bean
    public TopicExchange exchangeOne(){
        return new TopicExchange(oneExchange);
    }
    @Bean
    public Binding bindingOne(){
        return BindingBuilder
                .bind(queueOne())
                .to(exchangeOne())
                .with(oneRoutingKey);
    }
    @Bean
    public Queue queueTwo(){
        return new Queue(twoQueue);
    }
    @Bean
    public Binding bindingTwo(){
        return BindingBuilder
                .bind(queueTwo())
                .to(exchangeOne())
                .with(twoRoutingKey);
    }

    @Bean
    public Queue mobileQueue(){
        return new Queue(mobileQueue);
    }
    @Bean
    public TopicExchange mobileExchange(){
        return new TopicExchange(mobileExchange);
    }
    @Bean
    public Binding mobileBinding(){
        return BindingBuilder
                .bind(mobileQueue())
                .to(mobileExchange())
                .with(mobileRoutingKey);
    }
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
