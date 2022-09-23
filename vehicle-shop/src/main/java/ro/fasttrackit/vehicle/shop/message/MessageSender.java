package ro.fasttrackit.vehicle.shop.message;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicle.shop.model.OrderMapper;
import ro.fasttrackit.vehicle.shop.model.PartOrder;

@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final OrderMapper mapper;
    public void sendOrderToService(PartOrder order){
        rabbitTemplate.convertAndSend("order-exchange", "orders.materials.serv", mapper.toApi(order));
    }

}
