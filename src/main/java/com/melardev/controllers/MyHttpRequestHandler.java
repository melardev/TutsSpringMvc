package com.melardev.controllers;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpRequestHandler implements HttpRequestHandler {

    // HandlerMapping BeanNameUrlHandlerMapping
    // HandlerAdapter HttpRequestHandlerAdapter
    // ViewResolver None

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().write(("From " + getClass().getName()).getBytes());

    }
}
