package com.melardev.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class EventListener {

    @org.springframework.context.event.EventListener
    public void onContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Map<String, HandlerAdapter> handlerAdapters = context.getBeansOfType(HandlerAdapter.class);
        handlerAdapters.forEach(new BiConsumer<String, HandlerAdapter>() {
            @Override
            public void accept(String s, HandlerAdapter handlerAdapter) {
                if (handlerAdapter instanceof RequestMappingHandlerAdapter) {
                    ((RequestMappingHandlerAdapter) handlerAdapter).getMessageConverters()
                            .stream()
                            .forEach(System.out::println);
                }
            }
        });
    }

}
