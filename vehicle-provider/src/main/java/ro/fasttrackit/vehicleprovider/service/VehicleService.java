package ro.fasttrackit.vehicleprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vehicleprovider.model.Vehicle;
import ro.fasttrackit.vehicleprovider.repository.VehicleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repository;
    public Page<Vehicle> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Vehicle> getVehicleById(Integer id) {
        return repository.findById(id);
    }

    public Vehicle insertVehicle(Vehicle vehicle) {
        if(vehicle.getId() != null){
            vehicle.setId(null);
        }
        return repository.save(vehicle);
    }

    public Vehicle updateVehicle(Integer id, Vehicle vehicle) {
        Vehicle exist = repository.findById(id).orElseThrow(() -> new RuntimeException("could not find record with id=" + id));
        exist.setMake(vehicle.getMake());
        exist.setModel(vehicle.getModel());
        exist.setYear(vehicle.getYear());
        exist.setOwner(vehicle.getOwner());
        exist.setVin(vehicle.getVin());
        repository.save(exist);
        return exist;
    }
}
