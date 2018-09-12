package com.jiujiu.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SendServer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Bean
    public Queue queue(){
        return new Queue("hello");
    }


    public  void sendMsg(){
        amqpTemplate.convertAndSend("hello","hello rabbitmq");
    }
}
