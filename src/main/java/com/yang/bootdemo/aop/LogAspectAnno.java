package com.yang.bootdemo.aop;

import com.yang.bootdemo.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Aspect
@Component
public class LogAspectAnno {

    @Pointcut(value = "@annotation(com.yang.bootdemo.annotation.Log) || @within(com.yang.bootdemo.annotation.Log)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("执行了前置通知》》》》》》》》");
        //获取方法的参数
        Object[] args = joinPoint.getArgs();
        int len = args.length;
        Class[] classTypes = new Class[len];
        for (int i = 0; i <len ; i++) {
            classTypes[i] = args[i].getClass();
        }
        //获取方法名
        String signature = joinPoint.getSignature().getName();
        Log log = null;
        //获取注解类
        Class aclass = joinPoint.getSignature().getDeclaringType();
        //获取注解类上的注解
        Annotation[] declaredAnnotations = aclass.getDeclaredAnnotations();
        Annotation[] annotations = aclass.getAnnotations();
        //获取具体的类
        log =(Log)aclass.getAnnotation(Log.class);

        if(log == null) {
            //获取方法
            Method method = aclass.getMethod(signature, classTypes);
            //获取方法上的注解
            log = method.getAnnotation(Log.class);
        }
        //获取注解内的值
        String value = log.value();
        //执行方法
        Object proceed = joinPoint.proceed();
        System.out.println("执行了后置通知》》》》》》》》");
        return proceed;
    }
}
