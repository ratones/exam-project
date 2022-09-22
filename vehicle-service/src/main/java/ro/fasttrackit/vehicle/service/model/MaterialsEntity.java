package ro.fasttrackit.vehicle.service.model;

import lombok.Data;

@Data
public class MaterialsEntity {
    private String id;
    private String name;
    private String vehicleVin;
    private String details;
    private String category;
}
