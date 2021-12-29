package com.yang.bootdemo.Consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/***
 * 死信队列
 * 消息变成死信的情况：
 * 1.消息被拒绝
 * 2.消息过期
 * 3.队列达到最大长度
 * @author yang
 */
@Configuration
public class DeadRabbitMqConfig {

    @Bean
    public DirectExchange deadDirectExchange(){
        return new DirectExchange("dead-direct-exchange");
    }

    @Bean
    public Queue deadDirectQueue(){
        return new Queue("deadDirectQueue");
    }

    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(deadDirectExchange()).with("dead");
    }

}
