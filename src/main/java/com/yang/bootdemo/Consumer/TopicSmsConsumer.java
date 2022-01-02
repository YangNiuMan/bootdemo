package com.yang.bootdemo.Consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author yang
 */
@RabbitListener(bindings =@QueueBinding(value=@Queue(value="smsTopicQueue",durable = "true",
        autoDelete = "false"),exchange = @Exchange(value ="topic-exchange",type = ExchangeTypes.TOPIC),
        key = "#.topic"))
@Service
public class TopicSmsConsumer {

    @RabbitHandler
    public void reciveMessage(String msg, Channel channel, Message message){
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("分发式消息TopicSmsConsumer》》》》》"+message);
    }
}
