package ro.fasttrackit.vehicleprovider.message;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ro.fasttrackit.vehicleprovider.model.IOrderMapper;
import ro.fasttrackit.vehicleprovider.model.ServiceOrder;

@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final IOrderMapper mapper;
    public void sendOrderToService(ServiceOrder order){
        rabbitTemplate.convertAndSend("app-exchange", "orders", mapper.toApi(order));
    }
}
