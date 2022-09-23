package ro.fasttrackit.vehicle.shop.message;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WSMessageSender {
    private final SimpMessagingTemplate template;

    public void send(){
        template.convertAndSend("/topic/shoporder","reload");
    }
}
