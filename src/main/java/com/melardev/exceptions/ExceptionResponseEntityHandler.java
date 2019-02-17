package com.melardev.exceptions;

import com.melardev.models.Error;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.BeanExpressionException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

//@ControllerAdvice
public class ExceptionResponseEntityHandler extends ResponseEntityExceptionHandler {

    //@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(BeanExpressionException.class)
    @ResponseBody
    public String handleConflict() {
        return "Exception Handled outside the Controller";
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body != null)
            return new ResponseEntity<Object>(body.toString(), status);
        else
            return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, "handleTypeMismatch", headers, BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, "handleMissingServletRequestParameter", headers, BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, "handleHttpRequestMethodNotSupported", headers, BAD_REQUEST, request);
    }
}
