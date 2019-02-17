package com.melardev.services;

import com.melardev.models.ArticleXml;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


public class ArticlesXmlService {

    private ArrayList<ArticleXml> articles = new ArrayList<>();

    @PostConstruct
    public void init() {
        ArticleXml a1 = new ArticleXml(1, "Spring MVC", "Tutorials on Spring MVC");
        ArticleXml a2 = new ArticleXml(2, "Spring App", "tutorials on Spring Core");
        ArticleXml a3 = new ArticleXml(3, "Jersey Rest", "Tutorials on Jersey REST");

        articles.add(a1);
        articles.add(a2);
        articles.add(a3);
    }

    public java.util.Collection<ArticleXml> findAll() {
        return articles;
    }

    public ArticleXml find(int position) {
        if (position > 0 && position < articles.size())
            return articles.get(position);
        return null;
    }

    public ArticleXml add(ArticleXml articleXml) {
        articles.add(articleXml);
        return articleXml;
    }

    public boolean delete(int position) {
        if (position >= 0 && position < articles.size()) {
            articles.remove(position);
            return true;
        }
        return false;
    }

    public void update(int position, ArticleXml articleXml) {
        if (position >= 0 && position < articles.size()) {
            ArticleXml article = articles.get(position);
            article.setTitle(articleXml.getTitle());
            article.setDescription(articleXml.getDescription());
        }
    }
}
