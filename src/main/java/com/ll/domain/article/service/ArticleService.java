package com.ll.domain.article.service;

import com.ll.AppContext;
import com.ll.domain.article.entity.Article;
import com.ll.domain.article.repository.ArticleRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article write(String title, String content) {
        String regDate = LocalDate.now().format(AppContext.formatter);
        Article article = new Article(0, title, content, regDate);
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(int id) {
        return articleRepository.findById(id);
    }

    public Optional<Article> findByIdAndIncreaseViewCount(int id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            article.increaseViewCount();
            articleRepository.save(article);
        }
        return articleOptional;
    }

    public boolean update(int id, String title, String content) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            article.setTitle(title);
            article.setContent(content);
            articleRepository.save(article);
            return true;
        }
        return false;
    }

    public boolean deleteById(int id) {
        if (articleRepository.findById(id).isPresent()) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public int count() {
        return articleRepository.count();
    }

    public List<Article> search(String keyword) {
        return articleRepository.search(keyword);
    }
}