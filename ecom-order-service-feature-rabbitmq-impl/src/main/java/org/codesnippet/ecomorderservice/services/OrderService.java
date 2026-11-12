package org.codesnippet.ecomorderservice.services;

import com.netflix.appinfo.InstanceInfo;
import org.codesnippet.ecomorderservice.client.InventoryClient;
import org.codesnippet.ecomorderservice.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private InventoryService inventoryService;

    public String placeOrder(Long productId) throws ExecutionException, InterruptedException {

        Inventory inventory = inventoryService.getInventory(productId).get();
        int quantity = inventory.getQuantity();
        updateInventory(inventory);

        return  quantity>0?
              "Order Placed Successfully":
              "Product Out Of Stock";
    }


    private void updateInventory(Inventory inventory) {
        if (inventory.getQuantity() <= 0) {
            return;
        }
        inventory.setQuantity(inventory.getQuantity()-1);
        inventoryClient.updateInventory(inventory);

    }


}
