package com.yang.bootdemo.Producter.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/***
 * 点对点发送
 * @author yang
 */
@Configuration
public class DirectRabbitMqConfig {

    @Bean
    public DirectExchange directExchange(){
        Map<String, Object> args = new HashMap<>(8);
        args.put("alternate-exchange" , "backup-fanout-exchange");
        return new DirectExchange("direct-exchange", true, false, args);
    }

    @Bean
    public DirectExchange directExchange1(){
        return new DirectExchange("direct-exchange1", true, false);
    }

    /**
     * 备份交换器: 最好是fanout类型，防止消息丢失, 消息发送到队列失败，发到交换机
     * 备份交换机参数：alternate-exchange
     * @return
     */
    @Bean
    public FanoutExchange backupFanoutExchange(){
        return new FanoutExchange("backup-fanout-exchange");
    }

    @Bean
    public Queue backupFanoutQueue(){
        return new Queue("backupFanoutQueue");
    }


    @Bean
    public Queue smsDirectQueue(){
        return new Queue("smsDirectQueue");
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("emailDirectQueue");
    }

    /**
     * 优先级最低是0，最高为队列设置的最大优先级，优先级高的优先消费
     * @return
     */
    @Bean
    public Queue priorityDirectQueue(){
        Map<String, Object> args =new HashMap<String, Object>(8) ;
        args.put("x-max-priority",0);
        return new Queue("priorityDirectQueue", true, false, false, args);
    }


    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms-direct");
    }

    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email-direct");
    }

    @Bean
    public Binding backupFanoutBinding(){
        return BindingBuilder.bind(backupFanoutQueue()).to(backupFanoutExchange());
    }

}
