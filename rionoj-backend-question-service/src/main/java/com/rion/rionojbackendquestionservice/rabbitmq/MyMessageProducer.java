package com.rion.rionojbackendquestionservice.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 生产者
    public void sendMessage(String exchange, String routingKey, String message) {
        log.info("sendMessage message = {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
