package ro.fasttrackit.vehicle.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vehicle.service.model.OrderEntity;
import ro.fasttrackit.vehicle.service.service.OrderService;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService service;

    @GetMapping
    List<OrderEntity> getServiceOrders(String serviceType){
        return service.getServiceOrders(serviceType);
    }

    @GetMapping("{id}")
    OrderEntity getServiceOrder(@PathVariable String id){
        return service.getServiceOrder(id);
    }

    @PutMapping("{id}")
    OrderEntity updateOrder(@PathVariable String id, @RequestBody OrderEntity order){
        return service.updateOrder(id, order);
    }
}
