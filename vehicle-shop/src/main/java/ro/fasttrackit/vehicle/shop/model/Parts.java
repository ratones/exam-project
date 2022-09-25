package ro.fasttrackit.vehicle.shop.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parts {
    @Id
    private String id;
    private String name;
    private String details;
    private String category;
    private boolean available;
}
