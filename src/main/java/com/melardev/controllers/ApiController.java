package com.melardev.controllers;

import com.melardev.models.Article;
import com.melardev.models.ArticleXml;
import com.melardev.models.ArticlesXml;
import com.melardev.services.ArticlesXmlService;
import com.rometools.rome.feed.atom.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/api")
@Controller
public class ApiController {

    @Autowired
    ArticlesXmlService articlesService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());
        model.addAttribute("articles", articlesXml);
        return "articleView";
    }

    @RequestMapping(value = "/articles/add", method = RequestMethod.POST)
    public ModelAndView addArticle(@RequestBody ArticleXml articleXml) {
        articlesService.add(articleXml);
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());
        return new ModelAndView("articleView", "some", articlesXml);

    }

    @RequestMapping(value = "/articles/update/{position:\\d+}", method = RequestMethod.POST)
    public String updateArticle(@PathVariable("position") int position, @RequestBody ArticleXml articleXml, Model model) {
        articlesService.update(position, articleXml);
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());
        model.addAttribute("articles", articlesXml);
        return "articleView";
    }

    @GetMapping("/articles/delete/{position:\\d+}")
    public ModelAndView deleteArticle(@PathVariable("position") int position) {
        articlesService.delete(position);

        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());

        ModelAndView mav = new ModelAndView("articleView");
        mav.addObject("articles", articlesXml);
        // mav.getModel().put("articles", articlesXml);
        /*ModelAndView mav = new ModelAndView("articleView",
                BindingResult.MODEL_KEY_PREFIX + "articlesXml", articlesXml);
                */
        return mav;
    }

    @GetMapping("/articles/{position}")
    public ResponseEntity<ArticleXml> getArticleById(@PathVariable("position") int position) {
        ArticleXml article = articlesService.find(position);

        if (article == null) {
            return new ResponseEntity<>((ArticleXml) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(article, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/articles/nomarshal")
    @ResponseBody
    public ArticlesXml getArticlesWithoutMarshalView(Model model) {
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());
        return articlesXml;
    }

    @RequestMapping(value = "/articles/add/nomarshal", method = RequestMethod.POST)
    @ResponseBody
    public ArticlesXml addArticleWithoutMarshalView(@RequestBody ArticleXml articleXml) {
        articlesService.add(articleXml);
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());
        return articlesXml;
    }

    @RequestMapping(value = "/articles/update/nomarshal{position:\\d+}", method = RequestMethod.POST)
    @ResponseBody
    public ArticlesXml updateArticleWithoutMarshalView(@PathVariable("position") int position, @RequestBody ArticleXml articleXml) {
        articlesService.update(position, articleXml);
        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());
        return articlesXml; // Spring MVC has to return this as a body, so Spring DIC will search for
        // someone who can marshall it to something the user is asking(Accept: application/xml) and he will
        // find the Jaxb2RootElementHttpMessageConverter
    }

    @GetMapping("/articles/delete/nomarshal{position:\\d+}")
    @ResponseBody
    public ArticlesXml deleteArticleWithoutMarshalView(@PathVariable("position") int position) {
        articlesService.delete(position);

        ArticlesXml articlesXml = new ArticlesXml();
        articlesXml.addMembers(articlesService.findAll());

        ModelAndView mav = new ModelAndView("articleView");
        mav.addObject("articles", articlesXml);
        // mav.getModel().put("articles", articlesXml);
        /*ModelAndView mav = new ModelAndView("articleView",
                BindingResult.MODEL_KEY_PREFIX + "articlesXml", articlesXml);
                */
        return articlesXml;
    }

    @GetMapping("/articles/nomarshal{position}")
    @ResponseBody
    public ResponseEntity<ArticleXml> getArticleByIdWithoutMarshalView(@PathVariable("position") int position) {
        ArticleXml article = articlesService.find(position);

        if (article == null) {
            return new ResponseEntity<>((ArticleXml) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(article, HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/articles/nomarshal/{position}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<ArticleXml> getArticleJson(@PathVariable("position") int position) {
        ArticleXml article = articlesService.find(position);
        if (article == null) {
            return new ResponseEntity<>((ArticleXml) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(article, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/articles/nomarshal/{position}", headers = "Accept=application/atom+xml")
    @ResponseBody
    public ArticleXml getArticleFeed(@PathVariable("position") int position) {
        ArticleXml article = articlesService.find(position);
        if (article == null) {
            return null;
        }
        return article;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/articles/nomarshal/{position}")
    @ResponseBody
    public ArticleXml getArticleDefault(@PathVariable("position") int position) {
        ArticleXml article = articlesService.find(position);
        if (article == null) {
            return null;
        }
        return article;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/articles/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Article getArticleJson() {
        Article article = new Article();
        article.setTitle("Yet Another Spring MVC Tutorial");
        article.setBody("Json Tutorial why not");
        return article;
    }
}
