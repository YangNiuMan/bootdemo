package com.yang.bootdemo.config;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;


@Configuration
//@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("job-pool-%d").build();

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return Executors.newScheduledThreadPool(100);
        //return threadPoolExecutor;
    }

}
