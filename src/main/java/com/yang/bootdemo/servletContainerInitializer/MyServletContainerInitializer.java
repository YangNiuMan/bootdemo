package com.yang.bootdemo.servletContainerInitializer;

import com.yang.bootdemo.filter.MyFilter;
import com.yang.bootdemo.listener.MyListener;
import com.yang.bootdemo.servlet.MyServlet;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author yang
 * //必须使用非内嵌的tomcat服务器，打包成war启动，才能生效
 */
public class MyServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ServletRegistration.Dynamic myServlet = ctx.addServlet("MyServlet", MyServlet.class);
        myServlet.addMapping("/myServlet");
        FilterRegistration.Dynamic myFilter = ctx.addFilter("MyFilter", MyFilter.class);
        myFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true, new String[]{"/*"});
        ctx.addListener(MyListener.class);
    }
}
