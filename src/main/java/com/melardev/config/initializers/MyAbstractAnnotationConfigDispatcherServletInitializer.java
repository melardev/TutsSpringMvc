package com.melardev.config.initializers;

import com.melardev.config.AppConfig;
import com.melardev.config.RootConfig;
import org.springframework.lang.Nullable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/*
This is used when you have Java based configuration, if you have xml then you may want to use
extends AbstractDispatcherServletInitializer
 */
public class MyAbstractAnnotationConfigDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
/*
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet ds = new DispatcherServlet(servletAppContext);
        ds.setThrowExceptionIfNoHandlerFound(true); // to trigger our custom exception
        return ds;
    }*/

    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        //register Servlet based Filters
        //return new Filter[]{new LoggerFilter(), new CompresserFilter()};
        return super.getServletFilters();
    }
}