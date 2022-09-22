package ro.fasttrackit.vehicleprovider.message;

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
    TopicExchange orderExchange() {
        return new TopicExchange("order-exchange");
    }


    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue serviceOrders() {
        return new Queue("service-orders");
    }

    @Bean
    Queue repairOrders() {
        return new Queue("repair-orders");
    }


    @Bean
    Binding sendBinding(TopicExchange orderExchange, Queue serviceOrders) {
        return BindingBuilder.bind(serviceOrders).to(orderExchange).with("orders.service");
    }

    @Bean
    Binding receiveBinding(TopicExchange orderExchange, Queue repairOrders) {
        return BindingBuilder.bind(repairOrders).to(orderExchange).with("orders.repair");
    }
}
