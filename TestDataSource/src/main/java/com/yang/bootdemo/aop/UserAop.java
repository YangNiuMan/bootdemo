package com.yang.bootdemo.aop;

import contextHolder.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*切面处处理切换数据源*/
@Component
@Aspect
public class UserAop {

    @Around("execution(* com.yang.bootdemo.controller..*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入拦截》》》》》》》》》》》》》》");
        DataSourceContextHolder.setContextHolder("config");
        return pjp.proceed();
    }
}
