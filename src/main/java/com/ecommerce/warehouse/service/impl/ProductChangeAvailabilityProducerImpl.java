package com.ecommerce.warehouse.service.impl;

import com.ecommerce.warehouse.dto.StockStatusMessage;
import com.ecommerce.warehouse.service.IProductChangeAvailabilityProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductChangeAvailabilityProducerImpl implements IProductChangeAvailabilityProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKeyName;

    public ProductChangeAvailabilityProducerImpl(final RabbitTemplate rabbitTemplate,
                                                 @Value("${spring.rabbitmq.exchange.product-change-availability}") final String exchangeName,
                                                 @Value("${spring.rabbitmq.routink-key.product-change-availability}") final String routingKeyName) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyName = routingKeyName;
    }

    @Override
    public void notifyStatusChange(StockStatusMessage message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }
}

//      PAROU AULA 9 - IMPLEMENTANDO O PRODUCTSERVICE