package com.melardev.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "articles")
public class Article {

    @NotNull(message = "")
    @Size(min = 5)
    private String title;

    @Size(min = 6)
    @Max(value = 22, message = "")
    @Min(value = 6, message = "")
    @Pattern(regexp = "\\w+")
    private String body;

    @Email(message = "enter a valid email please")
    private String email;

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Article() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
