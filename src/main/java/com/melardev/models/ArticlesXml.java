package com.melardev.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@XStreamAlias("articlesX")
@XmlRootElement(name = "articles")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticlesXml {

    @XmlElement(name = "article_child")
    private List<ArticleXml> articles = new ArrayList<>();

    public List<ArticleXml> getMembers() {
        return articles;
    }

    public void setMembers(List<ArticleXml> articles) {
        this.articles = articles;
    }

    public void addMembers(Collection<ArticleXml> articles) {
        this.articles.addAll(articles);
    }
}
