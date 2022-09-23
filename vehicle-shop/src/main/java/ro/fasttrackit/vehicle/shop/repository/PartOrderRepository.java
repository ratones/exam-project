package ro.fasttrackit.vehicle.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.vehicle.shop.model.PartOrder;

public interface PartOrderRepository extends MongoRepository<PartOrder, String> {
}
