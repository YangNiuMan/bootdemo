package com.yang.bootdemo.config;

import com.yang.bootdemo.filter.MyFilter;
import com.yang.bootdemo.inteceptor.MyInterceptor;
import com.yang.bootdemo.listener.MyListener;
import com.yang.bootdemo.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public MyServlet myServlet(){
        return new MyServlet();
    }

    //自定义容器注册进入servlet容器
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(myServlet());
        bean.addUrlMappings("/myServlet");
        return bean;
    }

    @Bean
    public Filter myFilter() {
        return new MyFilter();
    }

    //过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //filterRegistrationBean.addUrlPatterns("/myservlet1","/myservlet");
        filterRegistrationBean.setFilter(myFilter());
        filterRegistrationBean.addUrlPatterns("/myservlet","/index");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public MyListener myListener() {
        return new MyListener();
    }

    //监听器
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean(myListener());
        return bean;
    }

    //拦截器，直接注册即可,实现 WebMvcConfigurer ，重写addInterceptors方法
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
}
