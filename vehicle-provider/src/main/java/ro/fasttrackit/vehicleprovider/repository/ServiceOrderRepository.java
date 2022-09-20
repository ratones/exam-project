package ro.fasttrackit.vehicleprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.vehicleprovider.model.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer> {
}