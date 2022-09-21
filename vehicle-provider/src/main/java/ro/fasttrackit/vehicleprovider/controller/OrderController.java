package ro.fasttrackit.vehicleprovider.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vehicleprovider.model.ServiceOrder;
import ro.fasttrackit.vehicleprovider.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("byVehicleId/{id}")
    List<ServiceOrder> getVehicleOrders(@PathVariable Integer id){
        return service.getVehicleOrder(id);
    }

    @PostMapping
    ServiceOrder insertServiceOrder(@RequestBody ServiceOrder order){
        return service.insertOrder(order);
    }

    @PutMapping("{id}")
    ServiceOrder updateOrder(@PathVariable Integer id,@RequestBody ServiceOrder order){
        return service.updateOrder(id, order);
    }

    @DeleteMapping("{id}")
    ServiceOrder deleteOrder(@PathVariable Integer id){
        return service.deleteOrder(id);
    }
}
