package org.codesnippet.ecominventoryservice.model;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        Long productId,
        int quantity
) {}