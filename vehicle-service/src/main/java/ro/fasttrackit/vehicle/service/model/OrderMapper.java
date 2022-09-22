package ro.fasttrackit.vehicle.service.model;

import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.service.repository.OrdersRepository;

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
}
