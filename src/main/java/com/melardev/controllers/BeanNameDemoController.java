package com.melardev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "/url_to_bean_name")
public class BeanNameDemoController extends AbstractController {

    // HandlerMapping BeanNameUrlHandlerMapping
    // HandlerAdapter SimpleControllerHandlerAdapter
    // ViewResolver InternalResourceViewResolver

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bean_named_controller");
        mav.addObject("message", "Unbelievable, this method handler is achieved anyways ... " +
                "thanks to BeanNameUrlHandlerMapping for the Mapping and the RequestMappingHandlerAdapter for the execution");
        return mav;
    }

}
