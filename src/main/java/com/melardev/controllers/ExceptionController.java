package com.melardev.controllers;

import org.springframework.beans.factory.BeanExpressionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exceptions")
public class ExceptionController {

    @GetMapping
    public String home(HttpServletRequest req) {
        System.out.println(req.getRequestURL());
        return "exceptions";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest_exception")
    public String handleRequest() {
        throw new IllegalArgumentException("from handleRequest");
    }

    @RequestMapping(value = "/null_pointer", method = RequestMethod.GET)
    public String getNotSupportedOp() {
        throw new NullPointerException();
    }

    @RequestMapping(value = "/default_exception", method = RequestMethod.GET)
    public String defaultException() {
        throw new ArrayIndexOutOfBoundsException();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public String httpMethodNotSupportedHandler() {
        return "basics";
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something wrong happened")
    public ResponseEntity<String> internalError(Exception ex) {
        return new ResponseEntity<String>("An Error happened in our Server, try again later."
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionForRest() {
        return new ResponseEntity<String>("<b>An Error</b> Happened, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Does not work, why? DispatcherServlet does not throw an exception on noHandlerFound by default, only a WARNING in the log
    // this can be done in a WebAppInitializer or ServletContainerInitializer subclass
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Never called");
        mav.setViewName("exceptions");
        return mav;
    }

    @ExceptionHandler
    public ModelAndView handleDefault(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Default Exception");
        mav.setViewName("exceptions");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bean_expression_exception")
    public String throwBeanExpressionException() {
        throw new BeanExpressionException("from handleRequest");
    }


}
