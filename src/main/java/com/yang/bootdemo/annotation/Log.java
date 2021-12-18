package com.yang.bootdemo.annotation;

import java.lang.annotation.*;

//
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value() default "null";
}
