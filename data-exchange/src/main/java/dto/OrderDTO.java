package dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderDTO {
    private Integer orderNo;
    private String category;
    private String status;
    private Date orderDate;
    private Date orderCompleted;
    private String notes;
    private Integer vehicleId;
}
