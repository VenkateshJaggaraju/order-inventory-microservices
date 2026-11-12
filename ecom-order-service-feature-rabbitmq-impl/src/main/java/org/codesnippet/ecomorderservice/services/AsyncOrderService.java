package org.codesnippet.ecomorderservice.services;

import org.codesnippet.ecomorderservice.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncOrderService {

    @Autowired
    private RabbitTemplate rabbitMQ;
    // now RabbitMQ knows where to connect app(u've given config in application.yaml file)

    public String placeOrder(Long productId) {

        //create a EVENT (each event has a message right?)
        OrderCreatedEvent event = new OrderCreatedEvent(1L, 1L, productId, 1);

        //use RabbitMQ to send created event
        rabbitMQ.convertAndSend("order.exchange",
                "order.created",
                event
        );

        return "Async order placed successfully";
    }
}
