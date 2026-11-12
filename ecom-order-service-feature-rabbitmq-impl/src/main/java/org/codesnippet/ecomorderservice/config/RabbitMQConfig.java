package org.codesnippet.ecomorderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("orderRabbitMQConfig")
public class RabbitMQConfig {


    public static final String EXCHANGE = "order.exchange";
    public static final String QUEUE = "inventory.queue";
    public static final String ROUTING_KEY = "order.created";//binding key
    //Automatically spring creates EXCHANGE, QUEUE, BINDING before/after API call
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue inventoryQueue(){
        return new Queue(QUEUE);
    }

    /*
     * if the event contains ROUTING_KEY/BINDING_KEY (eg. order.created)
     * then route it to the particular QUEUE (eg. inventory.queue)
     */

    @Bean
    public Binding inventoryBinding(Queue inventoryQueue, DirectExchange directExchange){
//        BindingBuilder bind the inventoryQueue to directExchange with ROUTING_KEY
        return BindingBuilder
                .bind(inventoryQueue)
                .to(directExchange)
                .with(ROUTING_KEY);
    }

    //it converts event message from AMQP
    @Bean
    public MessageConverter jacksonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
