package com.melardev.controllers;

import com.melardev.models.Article;
import com.melardev.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest-template")
public class ControllerForRestTemplate {

    @Autowired
    ArticlesService articlesService;

    @GetMapping("/articles/{id}")
    @ResponseBody
    public Article getArticleByPos(@PathVariable String id) {
        return articlesService.getArticleById(id);
    }

    @PostMapping("/articles")
    @ResponseBody
    public void addArticle(@RequestBody Article article) {
        articlesService.addArticle(article);
    }
}
