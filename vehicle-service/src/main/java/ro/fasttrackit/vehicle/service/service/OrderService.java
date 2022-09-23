package ro.fasttrackit.vehicle.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vehicle.service.messages.MessageSender;
import ro.fasttrackit.vehicle.service.model.OrderEntity;
import ro.fasttrackit.vehicle.service.repository.OrdersRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository repository;
    private final MessageSender messageSender;
    public List<OrderEntity> getServiceOrders(String serviceType) {
        return repository.findByCategory(serviceType);
    }

    public void insertOrder(OrderEntity orderEntity) {
        repository.save(orderEntity);
    }

    public OrderEntity getServiceOrder(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Could not find record with id " + id));
    }

    public OrderEntity updateOrder(String id, OrderEntity order) {
        OrderEntity current = repository.findById(id).orElseThrow(() -> new RuntimeException("Could not find record with id " + id));
        if(order.getStatus().equalsIgnoreCase("closed")){
            order.setDateCompleted(new Date());
        }
        if(!current.getStatus().equalsIgnoreCase(order.getStatus())){
            // send message to provider
            messageSender.sendOrderToProvider(order);
        }
        if(order.getStatus().equalsIgnoreCase("partsOrdered")){
            // send message to shop
            messageSender.sendOrderToShop(order);
        }

        return repository.save(order);
    }
}
