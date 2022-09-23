package ro.fasttrackit.vehicle.service.model;

import dto.MaterialOrderDTO;
import dto.MaterialsDTO;
import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.service.repository.OrdersRepository;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper implements IOrderMapper {
    private final OrdersRepository orderRepository;
    @Override
    public OrderDTO toApi(OrderEntity order) {
        return OrderDTO.builder()
                .orderCompleted(order.getDateCompleted())
                .orderNo(order.getOrderNo())
                .orderDate(order.getOrderDate())
                .category(order.getCategory())
                .status(order.getStatus())
                .vehicleId(order.getVehicleId())
                .vehicleVin(order.getVehicleVin())
                .notes(order.getNotes())
                .build();
    }

    @Override
    public OrderEntity toEntity(OrderDTO order) {
        OrderEntity entity = orderRepository.findByOrderNo(order.getOrderNo()).orElse(
                OrderEntity.builder()
                        .category(order.getCategory())
                        .orderDate(order.getOrderDate())
                        .orderNo(order.getOrderNo())
                        .notes(order.getNotes())
                        .status(order.getStatus())
                        .vehicleId(order.getVehicleId())
                        .vehicleVin(order.getVehicleVin())
                        .build()
        );
        entity.setDateCompleted(order.getOrderCompleted());
        entity.setStatus(order.getStatus());
        return entity;
    }

    @Override
    public OrderEntity toEntityFromShop(MaterialOrderDTO order) {
        OrderEntity local = orderRepository.findById(order.getOrderNo()).orElseThrow();
        local.setStatus(order.getStatus());
        return local;
    }

    @Override
    public MaterialOrderDTO fromEntityToShop(OrderEntity order) {
        MaterialOrderDTO newOrder = MaterialOrderDTO.builder()
                .orderNo(order.getId())
                .vehicleVin(order.getVehicleVin())
                .status(order.getStatus())
                .materials(order.getMaterials().stream().map(this::convertToMaterialDTO).collect(Collectors.toList()))
                .build();
        System.out.println(newOrder);
        return newOrder;
    }

    private MaterialsDTO convertToMaterialDTO(MaterialsEntity mat){
        return MaterialsDTO.builder()
                .category(mat.getCategory())
                .details(mat.getDetails())
                .name(mat.getDetails())
                .build();
    }
}
