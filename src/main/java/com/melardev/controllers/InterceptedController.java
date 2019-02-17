package com.melardev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intercepted")
public class InterceptedController {

    @GetMapping
    public String home() {
        return "intercepted";
    }

    @GetMapping("/request_attributes")
    public String getRequestAttributesFromInterceptor(@RequestAttribute("allowed") boolean isUserAllowed, Model model) {
        String message;
        if (isUserAllowed)
            message = "Secret message is .............. @RequestAttribute !!!";
        else
            message = "You are not allowed to access the secret message nop nop ...";
        model.addAttribute("message", message);
        return "intercepted";
    }
}
