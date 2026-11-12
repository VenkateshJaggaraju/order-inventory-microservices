package org.codesnippet.ecomorderservice.controller;

import com.netflix.discovery.converters.Auto;
import org.codesnippet.ecomorderservice.services.AsyncOrderService;
import org.codesnippet.ecomorderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AsyncOrderService asyncOrderService;

    @PostMapping("/{productId}")
    public String placeOrder(@PathVariable Long productId) throws ExecutionException, InterruptedException {
       return orderService.placeOrder(productId);
    }


//    Producer (OrderController) produces the event

    @PostMapping("/{productId}/async")
    public String placeOrderAsync(@PathVariable Long productId) throws ExecutionException, InterruptedException {
        return asyncOrderService.placeOrder(productId);
    }
}
