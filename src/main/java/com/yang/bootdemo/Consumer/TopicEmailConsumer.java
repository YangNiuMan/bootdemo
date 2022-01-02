package com.yang.bootdemo.Consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * @author yang
 */
@Service
@RabbitListener(queues = "emailTopicQueue")
public class TopicEmailConsumer {

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
