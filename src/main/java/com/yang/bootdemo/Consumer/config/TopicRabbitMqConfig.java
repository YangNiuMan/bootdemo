package com.yang.bootdemo.Consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 主题模式：发送到匹配成功路由键的队列
 * @author yang
 */
@Configuration
public class TopicRabbitMqConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Queue smsTopicQueue(){
        return new Queue("smsTopicQueue");
    }

    @Bean
    public Queue emailTopicQueue(){
        return new Queue("emailTopicQueue");
    }

    @Bean
    public Binding smsTopicBinding(){
        return BindingBuilder.bind(smsTopicQueue()).to(topicExchange()).with("#.topic");
    }

    @Bean
    public Binding emailTopicBinding(){
        return BindingBuilder.bind(emailTopicQueue()).to(topicExchange()).with("*.topic");
    }




}
