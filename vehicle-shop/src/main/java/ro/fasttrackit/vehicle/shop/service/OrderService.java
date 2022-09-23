package ro.fasttrackit.vehicle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vehicle.shop.model.PartOrder;
import ro.fasttrackit.vehicle.shop.repository.PartOrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final PartOrderRepository repository;
    public List<PartOrder> getOrders() {
        return repository.findAll();
    }

    public PartOrder updateOrder(String id, PartOrder order) {
        PartOrder current = repository.findById(id).orElseThrow();
        return repository.save(order);
    }

    public void insertOrder(PartOrder orderEntity) {
        repository.save(orderEntity);
    }
}
