package com.melardev.controllers;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@ResponseBody
@RequestMapping("/rest")
public class RestBasedController {

    @GetMapping
    public String home() {
        return "Hello to RestBasedController";
    }
}
