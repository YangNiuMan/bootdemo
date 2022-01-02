package com.yang.bootdemo.Consumer;

import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RabbitListener(queues = "smsDirectQueue")
@RabbitListener(queues = "emailDirectQueue")
@RabbitListener(queues = "backupFanoutQueue")
public class DirectConsumer {

    @RabbitHandler
    public void reciveMessage(String msg, Channel channel, Message message) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("msg》》》》》"+msg);
        System.out.println("channel》》》》》"+channel);
        System.out.println("message》》》》》"+message);
    }
}
