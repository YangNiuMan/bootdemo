package com.yang.bootdemo.Scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


//@EnableAsync
@org.springframework.core.annotation.Order(2)
@Component
public class ScheduledTask2 {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 5000)
    //@Async//异步，配合线程池实现并发
    public void task2() throws InterruptedException {
        //int i= 10/0;
        Thread current = Thread.currentThread();
        System.out.println("定时任务2开启" + sdf.format(new Date()) + current.getId() + ",id:" + current.getId());
        Thread.sleep(10000);
        System.out.println("定时任务2结束" + sdf.format(new Date()) + current.getId() + ",id:" + current.getId());
    }

}
