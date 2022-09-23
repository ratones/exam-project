package ro.fasttrackit.vehicle.service.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketSender {
    private final SimpMessagingTemplate template;

    public void send(){
        template.convertAndSend("/topic/srvorder","reload");
    }
}
