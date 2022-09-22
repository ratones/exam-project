package ro.fasttrackit.vehicle.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@Data
@Builder
public class OrderEntity {
    @Id
    private String id;
    private Integer orderNo;
    private Integer vehicleId;
    private String vehicleVin;
    private String category;
    private Date dateCompleted;
    private String status;
    private Date orderDate;
    private String notes;
    private List<MaterialsEntity> materials;
}
