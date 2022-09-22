package ro.fasttrackit.vehicle.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vehicle.service.messages.MessageSender;
import ro.fasttrackit.vehicle.service.model.OrderEntity;
import ro.fasttrackit.vehicle.service.repository.OrdersRepository;

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
        if(current.getStatus() != order.getStatus()){
            // send message to queue
            messageSender.sendOrderToProvider(order);
        }
        return repository.save(order);
    }
}
