package com.yang.bootdemo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;


/**
 * 前、后、异常、最终通知
 *
 * */
//@Component
///@Aspect
public class aspectAop {

    //@Pointcut("execution(public String com.example.sringboot_web.controller.*.*(..))")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @within(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        System.out.println("@Before"+joinPoint.getArgs());
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println("@After"+joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("@AfterReturning"+ result);
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(Exception exception){
        System.out.println("@AfterThrowing" + exception);
    }
}
