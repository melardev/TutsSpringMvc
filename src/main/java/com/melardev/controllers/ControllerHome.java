package com.melardev.controllers;

import com.melardev.models.Article;
import com.melardev.models.User;
import com.melardev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ControllerHome {
/*
    @RequestMapping("/")
    public String printHello(Model model) {
        model.addAttribute("message", "Hello World");
        return "index";
    }

    @RequestMapping("/redir")
    public String redirectMe() {
        return "redirect:/hello";
    }

    @RequestMapping(value = "/add_article", method = RequestMethod.POST)
    @ResponseBody
    public String addArticle(@ModelAttribute Article article, ModelMap model) {
        return "Added successfully<br />" + article.getTitle();
    }

    @RequestMapping(value = "/articles")
    public ModelAndView articles(ServletRequest request) {
        System.out.println(request.getLocalName());
        return new ModelAndView("add_articles", "command", new Article());
    }

    /**
     * Supported method argument annotations
     */

    @RequestMapping(value = "/params/{action}")
    public HttpEntity<String> requestAnnotations(@PathVariable("action") String action,
                                                 @RequestParam(name = "id", required = true) int id) {
        return new HttpEntity<String>("You requested For me to " + action + " with id " + id);
    }
/*
    @RequestMapping
    public String injectMeEverything(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                     Locale locale, WebRequest webRequest, InputStream is, OutputStream os, HttpMethod method,
                                     Principal principal, HttpEntity<?> httpEntity, Map outMap, Model outModel, ModelMap outModelMap,
                                     RedirectAttributes rattrs, Errors errors, BindingResult result, SessionStatus sStatus,
                                     UriComponentsBuilder uri) {

        return "injected_arguments";
    }

    /**
     * Supported returnted Types Model,ModelMap, String, View, string, etc.
     */
/*
    @RequestMapping(value = "/return/model")
    public Model returnTypeModelDemo(ServletRequest request) {
        return null;
    }

    @Autowired
    private WebApplicationContext webAppContext;

    public String springAppInfo(Model model) {
        model.addAttribute("", webAppContext.getServletContext().getServerInfo());
        model.addAttribute("", webAppContext.getBean("webAppVersion"));

        return "info";
    }

    @RequestMapping("/myviewCode")
    public String handleRequest(Model model) {
        model.addAttribute("msg", "A message from the controller");
        model.addAttribute("time", LocalTime.now());
        return "simpleView";
    }


    //required default to true, unless defaultValue is given
    @RequestMapping("{id}/messages")
    public String handleEmployeeMessagesRequest(@PathVariable(value = "id", required = true) String employeeId,
                                                @RequestParam Map<String, String> queryMap,
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("fromDate") LocalDate fromDate,
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("toDate") LocalDate toDate,
                                                @RequestParam(value = "other", defaultValue = "some") String some,
                                                Model model) {
        model.addAttribute("msg", "employee request by id and query map : " +
                employeeId + ", " + queryMap.toString());
        return "my-page";
    }
*/
/*
    @RequestMapping
    public String handleRequest(@CookieValue(value = "color", defaultValue = "#fff") String color, HttpServletRequest request, HttpServletResponse response, Model model) {
        // To read a cookie we only need the @CookieValue, for writing then in that case we need a HttpServletResponse

        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            Arrays.stream(cookies).forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
        System.out.println(color);

        Cookie newCookie = new Cookie("color", "testCookieValue");
        newCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(newCookie);
        model.addAttribute("color", color);
        return "cookies";
    }
*/
}
