package com.yang.bootdemo.controller;

import com.yang.bootdemo.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {




    // 消费监听
    @KafkaListener(topics = {"topic-test-llc"}, errorHandler = "consumerAwareErrorHandler")
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack){
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费Topic：" +record.topic() + "**分区"+record.partition()+ "**值内容"+ record.value());
        ack.acknowledge();
    }

    // 监听器
    @KafkaListener(id="timingConsumer",topics = "topic1",containerFactory = "delayContainerFactory")
    public void onMessage1(ConsumerRecord<?, ?> record){
        System.out.println("消费成功："+record.topic()+"-"+record.partition()+"-"+record.value());
    }

    // 消费监听
    @KafkaListener(topics = {"topic-test-llc1"}, errorHandler = "consumerAwareErrorHandler", containerFactory = "filterContainerFactory")
    public void onMessage1(ConsumerRecord<?, ?> record, Acknowledgment ack){
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费Topic：" +record.topic() + "**分区"+record.partition()+ "**值内容"+ record.value());
        ack.acknowledge();
    }

    /**
     * @Title 消息转发
     * @Description 从topic1接收到的消息经过处理后转发到topic2
     * @param record
     * @return
     */
    @KafkaListener(topics = {"topic-test-llc2"})
    @SendTo("topic-test-llc")
    public User onMessage3(ConsumerRecord<?,?> record){
        User user = new User();
        user.setUserName("杨杰");
        user.setPassword("123456");
        return user;
    }

    /**
     * @Title 指定topic、partition、offset消费
     * @Description 同时监听topic1和topic2，监听topic1的0号分区、
     * topic2的 "0号和1号" 分区，指向1号分区的offset初始值为8
     * 注意：topics和topicPartitions不能同时使用；
     * @param record
     */
    @KafkaListener(id="consumer1",groupId = "felix-group",topicPartitions = {
            @TopicPartition(topic = "topic1",partitions = {"0"}),
            @TopicPartition(topic = "topic2",partitions = "0",
                    partitionOffsets = @PartitionOffset(partition = "1",initialOffset = "8"))
    })
    public void onMessage2(ConsumerRecord<?,?> record){
        System.out.println("topic:"+record.topic()+"partition:"+record.partition()+"offset:"+record.offset()+"value:"+record.value());
    }
}
