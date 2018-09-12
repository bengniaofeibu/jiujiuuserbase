package com.jiujiu.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConstomMsg {

    @RabbitListener(queues = "hello")
    public void customMsg(String msg){
        System.out.println("============= 接受到的消息 "+msg);
    }
}
