package com.yang.bootdemo.annotation;

import java.lang.annotation.*;

/**
 * @author yang
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value() default "";
}
