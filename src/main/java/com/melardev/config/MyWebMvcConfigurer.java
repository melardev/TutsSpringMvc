package com.melardev.config;

import com.melardev.interceptors.MyInterceptor;
import com.melardev.interceptors.MyInterceptor2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan("com.melardev")
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        ViewControllerRegistration r = registry.addViewController("/");
        r.setViewName("index");
        r.setStatusCode(HttpStatus.OK);

        // Create Controller that has no body, it only has a Http Status Code set to Forbidden
        registry.addStatusController("/controller_from_webmvcconfigurer1", HttpStatus.FORBIDDEN);
        // Create controller mapped to /example, that Controller will redirect to /index
        registry.addRedirectViewController("/controller_from_webmvcconfigurer2", "/");

    }

    /*
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
             InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setOrder(10); //Higher the order, lower priority
            viewResolver.setViewClass(JstlView.class);
            viewResolver.setPrefix("/views/");
            viewResolver.setSuffix(".jsp");
            return viewResolver;
        }
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInterceptor interceptor1 = new MyInterceptor();
        MyInterceptor interceptor2 = new MyInterceptor();

        // Not needed anymore
        interceptor2.setUseExpiresHeader(true);
        interceptor2.setUseCacheControlHeader(true);
        interceptor2.setUseCacheControlNoStore(true);
        Properties cacheMappings = new Properties();
        cacheMappings.put("/**/*.css", "86400");
        cacheMappings.put("/**/*.png", "86400");
        cacheMappings.put("/**/*.jpg", "86400");
        interceptor2.setCacheMappings(cacheMappings);

        registry.addInterceptor(interceptor1).addPathPatterns("/intercepted/**");
        registry.addInterceptor(interceptor2).addPathPatterns("/intercepted/**", "/**/*.css");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");

        registry.addResourceHandler("/imgs/**")
                .addResourceLocations("/imgs/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("/fonts/");
    }
}
