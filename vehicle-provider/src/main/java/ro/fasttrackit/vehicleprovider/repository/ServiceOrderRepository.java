package ro.fasttrackit.vehicleprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.vehicleprovider.model.ServiceOrder;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer> {
    List<ServiceOrder> findByVehicleId(Integer vehicleId);
}