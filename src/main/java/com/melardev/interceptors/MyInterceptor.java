package com.melardev.interceptors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.io.IOException;

public class MyInterceptor extends WebContentInterceptor {

    public MyInterceptor() {
        setRequireSession(false);
        setSupportedMethods("GET", "POST", "OPTIONS", "HEAD");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {
        /*try {
            response.sendRedirect("some");

        } catch (IOException e) {
            e.printStackTrace();
        }
          return false;
          */
        String allowed = request.getParameter("allowed");
        if (allowed == null)
            request.setAttribute("allowed", false);
        else
            request.setAttribute("allowed", Boolean.parseBoolean(allowed));
        return true; // we are done, don't forward to next interceptor, by default is return false
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
