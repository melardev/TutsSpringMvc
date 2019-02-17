package com.melardev.config.initializers;

import com.melardev.config.AppConfig;
import com.melardev.config.RootConfig;
import com.melardev.config.WebSecurityConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/*
This class is automatically detected by the Servlet Container, so it will be triggered, here
you can set up the Spring MVC requirements, for example the registration of the front end Controller;:
DispatcherServlet. and this is done as any other Servlet.
But remember, only Servlet Containers v3.0+ allow us to configure things using code. Prior we need
to use xml files ...

Spring provides an implementation of ServletContainerInitializer which gets triggered by the Servlet
Container when the web app starts, then Spring framework will trigger own WebApplicationInitializer class
 */
public class MyWebApplicationInitializer {}/*implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext containerContext) throws ServletException {
        // if you want the old-school xml base config
        //XmlWebApplicationContext context = new XmlWebApplicationContext();
        //context.setConfigLocation("/WEB-INF/dispatcher-config.xml");

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        //context.scan("");
        containerContext.addListener(new ContextLoaderListener(rootContext));

        // create dispatcher context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(AppConfig.class);
        ServletRegistration.Dynamic servlet = containerContext.addServlet("dispatcher",
                new DispatcherServlet(dispatcherContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}*/
