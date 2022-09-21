package ro.fasttrackit.vehicle.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.vehicle.service.model.OrderEntity;

public interface OrdersRepository extends MongoRepository<OrderEntity, String> {
    OrderEntity findByOrderNo(Integer no);
}
