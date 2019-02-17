package com.melardev.controllers;

import com.melardev.models.User;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditor;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Controller
@RequestMapping("/metadata")
public class MetadataController {

    @Autowired
    WebApplicationContext dispatcherContext;

    @GetMapping
    public String home() {
        return "metadata";
    }

    @GetMapping("/context-info")
    public String contextInfo(HttpServletRequest request, Model model) {
        StringBuilder sb = new StringBuilder();
        WebApplicationContext rootContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

        User user1 = null, user2 = null;
        BasicsController thisController1 = null, thisController2 = null;
        try {
            user1 = (User) this.dispatcherContext.getBean("lol");

            if (rootContext != null)
                user2 = (User) rootContext.getBean("lol");

            thisController1 = (BasicsController) this.dispatcherContext.getBean("basicsController");
            if (rootContext != null)
                thisController2 = (BasicsController) rootContext.getBean("basicsController");
        } catch (NoSuchBeanDefinitionException ex) {
            sb.append("NoSuchBeanDefinition Exception triggered<br>");
        }

        if (user1 == user2)
            sb.append("User Bean residing in root context retrieved from both: dispatcher and root context<br />");

        if (thisController1 == thisController2)
            sb.append("basicsController Bean residing in dispatcher context retrieved from both: dispatcher and root context<br>");
        else if (thisController2 == null)
            sb.append("basicsController Bean residing in dispatcher context can't be retrieved from root context<br>");

        sb.append("<h4>Root WeApplicationContext</h4><br>");
        if (rootContext != null) {
            for (String name : rootContext.getBeanDefinitionNames())
                sb.append(name + "<br>");
        }
        sb.append("<h4>Servlet WebApplicationContext</h4><br>");
        for (String name : this.dispatcherContext.getBeanDefinitionNames())
            sb.append(name + "<br>");
        model.addAttribute("message", sb.toString());
        return "metadata";
    }

    @RequestMapping("/handler_mappings")
    public String listHandlerMappings(Model model) {
        Map<String, HandlerMapping> handlerMappingMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(dispatcherContext,
                HandlerMapping.class, true, false);

        StringBuilder buffer = new StringBuilder();
        handlerMappingMap.forEach(new BiConsumer<String, HandlerMapping>() {
            @Override
            public void accept(String key, HandlerMapping handlerMapping) {
                buffer.append(key).append(" --> ").append(handlerMapping.getClass().getName()).append("<br>");
            }
        });
        model.addAttribute("message", buffer.toString());

        return "metadata";
    }

    @RequestMapping("/property_editors")
    public String listPropertyEditors(Model model) {
        Map<String, PropertyEditor> propertyEditorMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(dispatcherContext,
                PropertyEditor.class, true, false);
        StringBuilder buffer = new StringBuilder();
        propertyEditorMap.forEach(new BiConsumer<String, PropertyEditor>() {
            @Override
            public void accept(String key, PropertyEditor propertyEditor) {
                buffer.append(key)
                        .append(" --> ")
                        .append(propertyEditor.getClass().getName())
                        .append("<br>");
            }
        });
        model.addAttribute("message", buffer.toString());
        return "metadata";
    }

    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @RequestMapping("/http_message_converters")
    public String listMessageConverters(Model model) {
        StringBuilder buffer = new StringBuilder();
        requestMappingHandlerAdapter.getMessageConverters().forEach(new Consumer<HttpMessageConverter<?>>() {
            @Override
            public void accept(HttpMessageConverter<?> httpMessageConverter) {
                buffer.append(httpMessageConverter.getClass().getName()).append("<br>");
            }
        });
        model.addAttribute("message", buffer.toString());
        return "metadata";
    }

    @RequestMapping("/handler_adapters")
    public String listHandlerAdapter(Model model) {
        Map<String, HandlerAdapter> handlerAdapterMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(dispatcherContext,
                HandlerAdapter.class, true, false);

        StringBuilder buffer = new StringBuilder();
        handlerAdapterMap.forEach(new BiConsumer<String, HandlerAdapter>() {
            @Override
            public void accept(String key, HandlerAdapter handlerAdapter) {
                buffer.append(key).append(" --> ").append(handlerAdapter.getClass().getName()).append("<br>");
            }
        });
        model.addAttribute("message", buffer.toString());
        return "metadata";
    }

    @RequestMapping("/dispatcher_context_beans")
    public String listDispatcherServletContextBeans(Model model) {

        StringBuilder buffer = new StringBuilder();
        Map<String, Object> objectMap = BeanFactoryUtils
                .beansOfTypeIncludingAncestors(dispatcherContext, Object.class, true, false);

        objectMap.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String key, Object object) {
                buffer.append(key + "-->" + object.getClass().getName()).append("<br>");
            }
        });
        model.addAttribute("message", buffer.toString());
        return "metadata";

    }

    @RequestMapping("/root_context_beans")
    public String listBeansInRootContext(HttpServletRequest request, Model model) {
        //WebApplicationContext rootContext = RequestContextUtils.findWebApplicationContext(request);
        WebApplicationContext rootContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

        Map<String, Object> objectMap = BeanFactoryUtils
                .beansOfTypeIncludingAncestors(rootContext, Object.class, true, false);

        StringBuilder buffer = new StringBuilder();
        objectMap.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String key, Object object) {
                buffer.append(key + "-->" + object.getClass().getName()).append("<br>");
            }
        });
        model.addAttribute("message", buffer.toString());
        return "metadata";
    }

}
