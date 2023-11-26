package com.yang.bootdemo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;

@Configuration
public class KafkaInitialConfiguration {


    //消息消费异常处理器
    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler(){
        return (message,exception,consumer)->{
            System.out.println("消费异常："+message.getPayload());
            return null;
        };
    }


    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("topic-test-llc",4, (short) 1 );
    }


    @Bean
    public NewTopic initialTopic1() {
        return new NewTopic("topic-test-llc1",4, (short) 1 );
    }

    @Bean
    public NewTopic initialTopic2() {
        return new NewTopic("topic-test-llc2",4, (short) 1 );
    }
    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
/*
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("testtopic",10, (short) 1 );
    }
*/

}
