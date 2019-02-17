package com.melardev.services;

import java.util.List;
import java.util.Map;

import com.melardev.models.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface ArticlesService {

	void updateArticle();
	List<Article> getAllArticles();
	List<Article> getArticlesByAuthor(String author);
	List<Article> getArticlesByFilter(Map<String, List<String>> filterParams);
	Article getArticleById(String articleId);
	void addArticle(Article product);
}
