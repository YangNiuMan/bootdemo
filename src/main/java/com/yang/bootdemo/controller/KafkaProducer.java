package com.yang.bootdemo.controller;

import com.yang.bootdemo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @GetMapping("/kafka/normal/{message}")
    public String sendMessage(@PathVariable("message") Integer normalMessage) {
            kafkaTemplate.send("topic-test-llc1", normalMessage);
        return "ok";
    }

    // 发送消息
    @GetMapping("/kafka/normal2/{message}")
    public String sendMessage2(@PathVariable("message") Integer normalMessage) {
        kafkaTemplate.send("topic-test-llc2", normalMessage);
        return "ok";
    }

    @PostMapping("/kafka/normalUser")
    public String normalUser(@RequestBody User user) {
        kafkaTemplate.send("topic-test-llc", user).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, fail -> {
            System.out.println("发送消息失败:" + fail.getMessage());
        });
        return "ok";
    }

    // 事务发送消息
    @GetMapping("/kafka/transaction/{message}")
    public void sendTransactionMessage(@PathVariable("message") String message) {
        //生命事务，后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations ->{
            operations.send("topic","test executeInTransaction");
            throw new RuntimeException("fail");
        });

        //不声明事务，后面保存但前端消息已经发送成功了
        kafkaTemplate.send("topic","test executeInTransaction");
        throw new RuntimeException("fail");
    }

}
