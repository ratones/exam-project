package ro.fasttrackit.vehicle.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vehicle.shop.model.PartOrder;
import ro.fasttrackit.vehicle.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    List<PartOrder> getOrders(){
        return service.getOrders();
    }

    @PutMapping("{id}")
    PartOrder updateOrder(@PathVariable String id, @RequestBody PartOrder order){
        return service.updateOrder(id, order);
    }

}
