package com.melardev.controllers;

import com.melardev.models.Article;
import com.melardev.models.User;
import com.sun.xml.internal.ws.api.policy.ValidationProcessor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/bindings")
public class BindingDemosController {

    @GetMapping
    public String getHome() {
        return "bindings/index";
    }

    @GetMapping("/model_attribute")
    public String getModelAttributeDemo() {
        return "bindings/model_attribute_article/article_add";
    }

    @ModelAttribute
    public void bindArticleToAllRequests(Model model) {
        model.addAttribute("subheader", "Article Management");
        model.addAttribute("article", new Article()); // to avoid IllegalStateException
    }

    @ModelAttribute("header_tut")
    public String bindSubHeader() {
        return "ModelAttribute Demo";
        // The same as model.addAttribute("header_tut", "ModelAttribute Demo");
    }

    @PostMapping(value = "/add_article_no_model_attribute")
    public String addArticle(
            @RequestParam("title") String title,
            @RequestParam("body") String body, Model model) {

        model.addAttribute("message", "Title: " + title + ", Body: " + body);
        return "bindings/model_attribute_article/article_add";
    }

    @PostMapping(value = "/add_article_model_attribute")
    public String addArticleWithModelAttribute(
            @Valid @ModelAttribute("article") Article article,
            BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            for (ObjectError err : result.getAllErrors())
                errors.add(err.getDefaultMessage());

            model.addAttribute("errors", errors);
        }
        model.put("message", "Article Object bound: title: " + article.getTitle());
        return "bindings/model_attribute_article/article_add";
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "bindings/validation/register_user";
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            for (ObjectError err : result.getAllErrors())
                errors.add(((FieldError) err).getField() + "::" + err.getCode() + " -> " + err.getDefaultMessage());

            model.addAttribute("errors", errors);
            return "bindings/validation/register_user";
        }
        return "bindings/validation/successful";
    }


    @RequestMapping(value = "/rediret_attributes/bad", method = {RequestMethod.GET, RequestMethod.POST})
    public String redirectAttributesBad(HttpMethod httpMethod, @Valid @ModelAttribute("user") User user,
                                        BindingResult result) {
        if (httpMethod == HttpMethod.POST && !result.hasErrors())
            return "redirect:/bindings/redirect/register?success=1&message=Congratulations you are registered";
        return "redirect:/bindings/redirect/register?success=0&message=Please fix the errors";
    }

    @RequestMapping(value = "/rediret_attributes/good", method = {RequestMethod.GET, RequestMethod.POST})
    public String redirectAttributesGood(HttpMethod httpMethod, @Valid @ModelAttribute("user") User user,
                                         BindingResult result, RedirectAttributes redirectAttributes) {
        if (httpMethod == HttpMethod.POST && !result.hasErrors()) {
            redirectAttributes.addFlashAttribute("success", 1);
            redirectAttributes.addFlashAttribute("message", "Good to go");
            return "bindings/redirect/register?success=1&message=Congratulations you are registered";
        }

        redirectAttributes.addFlashAttribute("success", 0);
        redirectAttributes.addFlashAttribute("message", "Bad");
        return "bindings/redirect/register";
    }

    // Using request param that matches user properties
    public String bindModel(User user, Model model) {
        return "bindings/index";
    }

    public String defaultConverter() {
        return "bindings/default_converter";
    }

    public String customConverter(User user, Model model) {
        model.addAttribute("message", "first name: " + user.getFirstName()
                + "last name: " + user.getLastName());
        return "bindings/index";
    }

}
