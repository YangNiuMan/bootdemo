package com.yang.bootdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yang
 */
@Slf4j
@Component
public class MqSendMsg implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{


    private RabbitTemplate rabbitTemplate;

    public MqSendMsg(@Autowired RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setReturnsCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("correlationData>>>>>>>>>>>>{}",correlationData);
        log.info("ack>>>>>>>>>>>>{}",ack);
        log.info("cause>>>>>>>>>>>>{}",cause);
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("returned>>>>>>>>>>>>{}",returned);
    }


    public void sendMsg(){
        rabbitTemplate.convertAndSend("direct-exchange1", "sms-direct1", "sms-direct消息发送测试");
    }
}
