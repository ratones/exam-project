package ro.fasttrackit.vehicle.service.messages;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("app-exchange");
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue studentEvents() {
        return new Queue("orders-events");
    }

    @Bean
    Binding studentBinding(TopicExchange topicExchange, Queue studentEvent) {
        return BindingBuilder.bind(studentEvent).to(topicExchange).with("orders");
    }
}
