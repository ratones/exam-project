package ro.fasttrackit.vehicle.service.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.service.model.IOrderMapper;
import ro.fasttrackit.vehicle.service.model.OrderEntity;

@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final IOrderMapper mapper;
    public void sendOrderToProvider(OrderEntity order){
        rabbitTemplate.convertAndSend("order-exchange", "orders.repair", mapper.toApi(order));
    }
}