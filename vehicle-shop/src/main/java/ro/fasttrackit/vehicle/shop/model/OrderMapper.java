package ro.fasttrackit.vehicle.shop.model;

import dto.MaterialOrderDTO;
import dto.MaterialsDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public MaterialOrderDTO toApi(PartOrder order){
        return MaterialOrderDTO.builder()
                .orderNo(order.getOrderNo())
                .status(order.getStatus())
                .vehicleVin(order.getVehicleVin())
                .materials(order.getParts().stream().map(this::convertMaterialsToDTO).collect(Collectors.toList()))
                .build();
    }

    public PartOrder toEntity(MaterialOrderDTO order){
        return  PartOrder.builder()
                .parts(order.getMaterials().stream().map(this::convertMaterialsFromDTO).collect(Collectors.toList()))
                .orderNo(order.getOrderNo())
                .vehicleVin(order.getVehicleVin())
                .status(order.getStatus())
                .build();
    }

    private MaterialsDTO convertMaterialsToDTO(Parts parts) {
        return MaterialsDTO.builder()
                .name(parts.getName())
                .details(parts.getDetails())
                .category(parts.getCategory())
                .build();
    }

    private Parts convertMaterialsFromDTO(MaterialsDTO parts) {
        return Parts.builder()
                .name(parts.getName())
                .details(parts.getDetails())
                .category(parts.getCategory())
                .build();
    }
}
