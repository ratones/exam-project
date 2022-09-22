package ro.fasttrackit.vehicleprovider.model;

import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicleprovider.repository.DeficienciesRepository;
import ro.fasttrackit.vehicleprovider.repository.ServiceOrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper implements IOrderMapper {
    private final ServiceOrderRepository orderRepository;
    private final DeficienciesRepository deficienciesRepository;
    @Override
    public OrderDTO toApi(ServiceOrder order) {
        List<String> defs = deficienciesRepository.findByOrderId(order.getId()).stream().map(d -> d.getDescription()).collect(Collectors.toList());
        return OrderDTO.builder()
                .orderCompleted(order.getDateCompleted())
                .orderNo(order.getId())
                .orderDate(order.getOrderDate())
                .category(order.getCategory().name())
                .status(order.getStatus().name())
                .vehicleId(order.getVehicle().getId())
                .vehicleVin(order.getVehicle().getVin())
                .notes(order.getNotes())
                .deficiencies(defs)
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
