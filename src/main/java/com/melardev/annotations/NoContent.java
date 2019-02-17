package com.melardev.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // has to be used over a method( it targets a method)
@Documented // this annotation should be documented by JavaDoc tool
@RequestMapping(method = RequestMethod.GET)
@ResponseStatus(HttpStatus.NO_CONTENT)
public @interface NoContent {
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};
}
