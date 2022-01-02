package com.yang.bootdemo.Producter.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 广播：会发送到所有的队列
 * @author yang
 */
@Configuration
public class FanoutRabbitMqConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Queue smsFanoutQueue(){
        return new Queue("smsFanoutQueue");
    }

    @Bean
    public Queue emailFanoutQueue(){
        return new Queue("emailFanoutQueue");
    }

    @Bean
    public Binding smsFanoutBinding(){
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailFanoutBinding(){
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }


}
