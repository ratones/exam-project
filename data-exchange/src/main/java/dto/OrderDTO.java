package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer orderNo;
    private String category;
    private String status;
    private Date orderDate;
    private Date orderCompleted;
    private String notes;
    private Integer vehicleId;
}
