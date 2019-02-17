package com.melardev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ControllerTest {

    @GetMapping
    @ResponseBody
    public String getTest() {
        return "that is it";
    }
}
