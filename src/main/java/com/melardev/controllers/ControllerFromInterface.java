package com.melardev.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ControllerFromInterface implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> model = mav.getModel();
        model.put("message", request.getParameter("page"));
        mav.setViewName("bean_named_controller");

        return mav;
    }

}
