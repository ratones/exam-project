package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialOrderDTO {
    private String orderNo;
    private String vehicleVin;
    private List<MaterialsDTO> materials;
    private String status;
}
