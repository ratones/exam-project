package ro.fasttrackit.vehicle.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parts {
    private String id;
    private String name;
    private String details;
    private String category;
    private boolean available;
}
