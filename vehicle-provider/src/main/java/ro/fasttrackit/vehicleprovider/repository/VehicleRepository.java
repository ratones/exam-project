package ro.fasttrackit.vehicleprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.vehicleprovider.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}