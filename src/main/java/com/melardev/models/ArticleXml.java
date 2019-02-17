package com.melardev.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlRootElement;

@XStreamAlias("articleXStream")
//@XmlRootElement(name="article")
public class ArticleXml {
    private int id;
    private String title;
    private String description;

    public ArticleXml(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ArticleXml() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
