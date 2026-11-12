package org.codesnippet.ecominventoryservice.listener;

import org.codesnippet.ecominventoryservice.model.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderCreatedListener {

    //Consumer consumes/listens the event from QUEUE
    @RabbitListener(queues = "inventory.queue")
    public void handleOrderCreatedEvent(OrderCreatedEvent event){
        System.out.println("Received event: "+event);
    }

}
