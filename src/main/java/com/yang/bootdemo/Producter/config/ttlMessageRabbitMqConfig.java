package com.yang.bootdemo.Producter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yang
 */
@Configuration
public class ttlMessageRabbitMqConfig {

    /**
     * 交换机
     * @return
     */
    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl-direct-exchange");
    }

    /**
     * 消息过期绑定死信队列
     * 延迟队列思路：
     * 消息设置过期时间，然后绑定死信交换器，绑定死信队列，过期的消息会投递到死信队列然后直接消费
     * @return
     */
    @Bean
    public Queue ttlDirectQueue(){
        Map<String,Object> map = new HashMap<>(8);
        map.put("x-message-ttl",50000);
        map.put("x-dead-letter-exchange","dead-direct-exchange");
        map.put("x-dead-letter-routing-key","dead");
        return new Queue("ttlDirectQueue",true,false,false,map);
    }

    /**
     * 队列过期队列
     * @return
     */
    @Bean
    public Queue ttlQueueQueue(){
       Map<String, Object> map = new HashMap<>(8);
       map.put("x-message-ttl",10000);
       return  new Queue("ttlQueueQueue",true,false,false, map);
    }

    /**
     * 消息过期队列
     * @return
     */
    @Bean
    public Queue ttlMessageQueue(){
        return  new Queue("ttlMessageQueue");
    }

    @Bean
    public Binding ttlQueueBinding(){
        return BindingBuilder.bind(ttlQueueQueue()).to(ttlDirectExchange()).with("ttlQueue");
    }

    @Bean
    public Binding ttlMessageBinding(){
        return BindingBuilder.bind(ttlMessageQueue()).to(ttlDirectExchange()).with("ttlMessage");
    }

    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue()).to(ttlDirectExchange()).with("ttlDeadExchange");
    }
}
