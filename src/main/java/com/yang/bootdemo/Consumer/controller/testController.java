package com.yang.bootdemo.Consumer.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 点对点发送
     * @return
     */
    @RequestMapping("direct")
    public String direct(){
        rabbitTemplate.convertAndSend("direct-exchange", "sms-direct", "sms-direct消息发送测试");
        rabbitTemplate.convertAndSend("direct-exchange", "email-direct", "email-direct消息发送测试");
        rabbitTemplate.convertAndSend("direct-exchange", "email-direct12", "email-direct消息发送测试");
        return "direct发送成功";
    }

    /**
     * 广播测试
     * @return
     */
    @RequestMapping("fanout")
    public String fanout(){
        rabbitTemplate.convertAndSend("fanout-exchange","fanout-exchange", "fanout消息发送测试");
        return "fanout发送成功";
    }

    /**
     * 主题模式：*代表匹配任意一个关键词，#代表匹配一个或多个关键词
     * 绑定用通配符的方式
     * @return
     */
    @RequestMapping("topic")
    public String topic(){
        rabbitTemplate.convertAndSend("topic-exchange","sms.topic", "topic消息发送测试");
        return "topic发送成功";
    }

    /**
     * 队列过期测试，若消息和队列都设置了，以时间短的为主
     * @return
     */
    @RequestMapping("ttlQueue")
    public String ttlQueue(){
        rabbitTemplate.convertAndSend("ttl-direct-exchange","ttlQueue", "ttlQueue消息发送测试");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("5000");
        Message message = new Message("ttlMessage消息发送测试".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("ttl-direct-exchange","ttlQueue", message);
        return "ttlQueue发送成功";
    }

    /**
     * 消息单独设置过期
     * @return
     */
    @RequestMapping("ttlMessage")
    public String ttlMessage(){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("50000");
        Message message = new Message("ttlMessage消息发送测试".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("ttl-direct-exchange","ttlMessage", message);
        return "ttlMessage发送成功";
    }

    /**
     * 消息失效进入死信队列测试
     * @return
     */
    @RequestMapping("ttlDeadExchange")
    public String ttlDeadExchange(){
        rabbitTemplate.convertAndSend("ttl-direct-exchange","ttlDeadExchange", "ttlDeadExchange消息发送测试");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("50000");
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message message = new Message("ttlMessage消息发送测试".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("ttl-direct-exchange","ttlDeadExchange", message);
        return "ttlDeadExchange发送成功";
    }
}
