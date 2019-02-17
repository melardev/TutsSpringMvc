package com.melardev.controllers;

import com.melardev.models.ArticleXml;
import com.melardev.models.ArticlesXml;
import com.melardev.services.ArticlesXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Collection;

@Controller
@RequestMapping("/api-xstream")
public class XStreamController {

    @Autowired
    ArticlesXmlService articlesService;

    @RequestMapping("/articles")
    //@ResponseBody
    public String getArticles(Model model) {
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());

        // ModelAndView modelAndView = new ModelAndView("articleView", BindingResult.MODEL_KEY_PREFIX + "articleXml", articlesXml);
        // return modelAndView;

        model.addAttribute("articles", articlesXml);
        return "articleView";
        //return articlesXml; // Spring MVC has to return this as a body, so Spring DIC will search for
        // someone who can marshall it to something the user is asking(Accept: application/xml) and he will
        // find the Jaxb2RootElementHttpMessageConverter
    }

    @PostMapping(value = "/articles", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ArticleXml> addArticle(@RequestBody ArticleXml article) {
        articlesService.add(article);
        return new ResponseEntity<ArticleXml>(article, HttpStatus.OK);
    }

    @GetMapping(value = "/articles/{articleId}")
    public ArticleXml getSpecific(@PathVariable("articleId") int articleId) {
        ArticleXml article = articlesService.find(articleId);
        return article;
    }

    @GetMapping("/articles/{id}/entity")
    //@ResponseBody if class annotated with @Controller
    public ResponseEntity<ArticleXml> getArticle(@PathVariable("id") int id, Model model) {
        ArticleXml article = articlesService.find(id);
        if (article != null)
            return new ResponseEntity<ArticleXml>(article, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<String> removeSpecific(@PathVariable("articleId") int articleId) {
        if (articlesService.delete(articleId))
            return ResponseEntity.ok("Success");
        else
            return ResponseEntity.badRequest().body("Failed");
    }


    @RequestMapping(value = "/articles/{articleId}/mav", method = RequestMethod.GET)
    public ModelAndView getArticleById(@PathVariable("articleId") int articleId) {
        //The Model will be ArticleXml, it will be marshalled, then embedded into the MarshallingView
        // Marshaller is XStreamMarshaller
        ArticleXml article = articlesService.find(articleId);
        //ModelAndView mav = new ModelAndView("articleView");
        ModelAndView mav = new ModelAndView("articleView", "myModel", article);
        mav.addObject(article);
        return mav;
    }
}
