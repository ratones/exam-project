package ro.fasttrackit.vehicleprovider.message;

import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicleprovider.model.IOrderMapper;
import ro.fasttrackit.vehicleprovider.model.ServiceOrder;
import ro.fasttrackit.vehicleprovider.service.OrderService;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final IOrderMapper mapper;
    private final OrderService service;

    private final WebSocketSender webSocketSender;

    @RabbitListener(queues = "repair-orders")
    public void consumeMessageFromQueue(OrderDTO order) {
        ServiceOrder orderEntity = mapper.toEntity(order);
        System.out.println("Message Received from queue: " + orderEntity.toString() );
        service.updateOrder(orderEntity.getId(),orderEntity);
        webSocketSender.send();
    }
}
