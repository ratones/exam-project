package ro.fasttrackit.vehicle.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.vehicle.service.model.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends MongoRepository<OrderEntity, String> {
    Optional<OrderEntity> findByOrderNo(Integer no);

    List<OrderEntity> findByCategory(String serviceType);
}
