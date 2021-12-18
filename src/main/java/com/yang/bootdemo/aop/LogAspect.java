package com.yang.bootdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/***
 * 环绕通知
 */
//@Aspect
//@Component
public class LogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @within(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object  arounnd(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前置》》》");
        Object proceed = joinPoint.proceed();
        System.out.println("后置》》》");
        return proceed;
    }
}
