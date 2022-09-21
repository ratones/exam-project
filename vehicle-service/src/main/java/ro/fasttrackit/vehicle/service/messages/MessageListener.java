package ro.fasttrackit.vehicle.service.messages;

import dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.service.model.IOrderMapper;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final IOrderMapper mapper;

    @RabbitListener(queues = "orders-events" )
    public void consumeMessageFromQueue(OrderDTO order) {
        System.out.println("Message Received from queue: " + mapper.toEntity(order).toString() );
    }
}
