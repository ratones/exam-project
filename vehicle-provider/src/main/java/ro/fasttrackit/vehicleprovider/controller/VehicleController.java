package ro.fasttrackit.vehicleprovider.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.vehicleprovider.model.Vehicle;
import ro.fasttrackit.vehicleprovider.service.VehicleService;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @GetMapping
    Page<Vehicle> getAll(Pageable pageable){
        return service.getAll(pageable);
    }

    @GetMapping("{id}")
    Vehicle getVehicle(@PathVariable Integer id){
        return service.getVehicleById(id).orElseThrow(() -> new RuntimeException("Could not find record with id=" + id));
    }

    @PostMapping
    Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return service.insertVehicle(vehicle);
    }

    @PutMapping("{id}")
    Vehicle updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle){
        return service.updateVehicle(id, vehicle);
    }
}
