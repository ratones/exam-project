package ro.fasttrackit.vehicle.shop.message;


import dto.MaterialOrderDTO;
import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.shop.model.OrderMapper;
import ro.fasttrackit.vehicle.shop.model.PartOrder;
import ro.fasttrackit.vehicle.shop.service.OrderService;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final OrderMapper mapper;
    private final OrderService service;

    private final WSMessageSender webSocketSender;

    @RabbitListener(queues = "material-shop-orders" )
    public void consumeMessageFromQueue(MaterialOrderDTO order) {
        PartOrder orderEntity = mapper.toEntity(order);
        System.out.println("Message Received from queue: " + orderEntity.toString() );
        service.insertOrder(orderEntity);
        webSocketSender.send();
    }

}
