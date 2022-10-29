package com.yang.bootdemo.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaProducerSendResultListener implements ProducerListener {

	@Override
	public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
		System.out.println("消息发送成功 : " + producerRecord.toString());
	}

	@Override
	public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata, Exception exception) {
		System.out.println("消息发送成功，exception=" + exception.getMessage());
	}
}
