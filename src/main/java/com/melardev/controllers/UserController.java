package com.melardev.controllers;

import com.melardev.models.User;
import com.melardev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handlePostRequest(@Valid @ModelAttribute("user") com.melardev.beans.User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors())
            return "user/register";

        userService.saveUser(user);
        return "user/register-done";
    }

}
