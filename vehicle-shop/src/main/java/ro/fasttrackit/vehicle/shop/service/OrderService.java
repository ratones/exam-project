package ro.fasttrackit.vehicle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vehicle.shop.message.MessageSender;
import ro.fasttrackit.vehicle.shop.model.PartOrder;
import ro.fasttrackit.vehicle.shop.repository.PartOrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final PartOrderRepository repository;
    private final MessageSender sender;
    public List<PartOrder> getOrders() {
        return repository.findAll();
    }

    public PartOrder updateOrder(String id, PartOrder order) {
        PartOrder current = repository.findById(id).orElseThrow();
        if(order.getStatus().equalsIgnoreCase("partsDelivered")){
            sender.sendOrderToService(order);
        }
        return repository.save(order);
    }

    public void insertOrder(PartOrder orderEntity) {
        orderEntity.getParts().forEach(p -> p.setId(String.valueOf(UUID.randomUUID())));
        repository.save(orderEntity);
    }
}
