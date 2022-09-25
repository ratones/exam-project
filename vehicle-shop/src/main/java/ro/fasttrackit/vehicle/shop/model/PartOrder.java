package ro.fasttrackit.vehicle.shop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "parts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartOrder {

    @Id
    private String id;
    private String orderNo;
    private String vehicleVin;

    private List<Parts> parts;
    private String status;
}
