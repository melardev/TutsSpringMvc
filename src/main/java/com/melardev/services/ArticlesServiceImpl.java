package com.melardev.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melardev.dao.ArticleRepository;
import com.melardev.models.Article;

@Service
public class ArticlesServiceImpl implements ArticlesService {

	@Autowired
	private ArticleRepository articleRepository;

	public void updateArticle() {

	}

	public List<Article> getAllArticles() {
		return articleRepository.getAllArticles();
	}

	public List<Article> getArticlesByAuthor(String author) {
		return articleRepository.getArticlesByAuthor(author);
	}

	public List<Article> getArticlesByFilter(Map<String, List<String>> filterParams) {
		return null;
	}

	public Article getArticleById(String articleId) {
		return articleRepository.getArticleById(articleId);
	}

	public void addArticle(Article article) {
		articleRepository.addArticle(article);
	}

}
