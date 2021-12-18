package com.yang.bootdemo.Scheduled;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@EnableAsync
@org.springframework.core.annotation.Order(1)
@Component
public class ScheduledTask1 {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    //上次执行完成后1秒后再执行(参数为字符串，可以从配置文件读取)
    @Scheduled(fixedDelayString = "5000")
    //@Async
    public void task1() throws InterruptedException {
            Thread current = Thread.currentThread();
            System.out.println(" 定时任务1 开始执行时间 "+sdf.format(new Date())+"线程id是："+current.getId());

            Thread.sleep(5000);
            System.out.println(" 定时任务1 结束"+sdf.format(new Date())+"-----"+current.getId()+ ",id:"+current.getId());

    }

}
