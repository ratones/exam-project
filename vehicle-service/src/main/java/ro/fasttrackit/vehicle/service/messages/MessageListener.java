package ro.fasttrackit.vehicle.service.messages;

import dto.MaterialOrderDTO;
import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.service.model.IOrderMapper;
import ro.fasttrackit.vehicle.service.model.OrderEntity;
import ro.fasttrackit.vehicle.service.service.OrderService;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final IOrderMapper mapper;
    private final OrderService service;

    private final WebSocketSender webSocketSender;

    @RabbitListener(queues = "service-orders" )
    public void consumeMessageFromQueue(OrderDTO order) {
        OrderEntity orderEntity = mapper.toEntity(order);
        System.out.println("Message Received from queue: " + orderEntity.toString() );
        service.insertOrder(orderEntity);
        webSocketSender.send();
    }

    @RabbitListener(queues = "material-serv-orders" )
    public void consumeMessageFromQueue(MaterialOrderDTO order) {
        OrderEntity orderEntity = mapper.toEntityFromShop(order);
        System.out.println("Message Received from queue: " + orderEntity.toString() );
        service.updateOrder(orderEntity.getId(),orderEntity);
        webSocketSender.send();
    }
}
