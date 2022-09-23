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
    Queue materialServOrders() {
        return new Queue("material-serv-orders");
    }

    @Bean
    Queue materialShopOrders() {
        return new Queue("material-shop-orders");
    }

    @Bean
    Binding sendBinding(TopicExchange orderExchange, Queue repairOrders) {
        return BindingBuilder.bind(repairOrders).to(orderExchange).with("orders.repair");
    }

    @Bean
    Binding receiveBinding(TopicExchange orderExchange, Queue serviceOrders) {
        return BindingBuilder.bind(serviceOrders).to(orderExchange).with("orders.service");
    }

    @Bean
    Binding receiveMaterialBinding(TopicExchange orderExchange, Queue materialServOrders) {
        return BindingBuilder.bind(materialServOrders).to(orderExchange).with("orders.materials.serv");
    }

    @Bean
    Binding sendMaterialBinding(TopicExchange orderExchange, Queue materialShopOrders) {
        return BindingBuilder.bind(materialShopOrders).to(orderExchange).with("orders.materials.shop");
    }
}
