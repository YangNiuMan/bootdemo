package com.yang.bootdemo.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器开始初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器开始执行逻辑");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器开始执行销毁");
    }
}
