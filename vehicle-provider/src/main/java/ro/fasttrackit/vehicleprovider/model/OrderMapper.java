package ro.fasttrackit.vehicleprovider.model;

import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicleprovider.repository.ServiceOrderRepository;

@Component
@RequiredArgsConstructor
public class OrderMapper implements IOrderMapper {
    private final ServiceOrderRepository orderRepository;
    @Override
    public OrderDTO toApi(ServiceOrder order) {
        return OrderDTO.builder()
                .orderCompleted(order.getDateCompleted())
                .orderNo(order.getId())
                .orderDate(order.getOrderDate())
                .category(order.getCategory().name())
                .status(order.getStatus().name())
                .vehicleId(order.getVehicle().getId())
                .notes(order.getNotes())
                .build();
    }

    @Override
    public ServiceOrder toEntity(OrderDTO order) {
        ServiceOrder entity = orderRepository.findById(order.getOrderNo()).orElseThrow();
        entity.setDateCompleted(order.getOrderCompleted());
        entity.setStatus(OrderStatus.valueOf(order.getStatus()));
        return entity;
    }
}
