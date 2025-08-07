package com.ll.domain.article.repository;

import com.ll.domain.article.entity.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public Article save(Article article) {
        if (article.getId() == 0) {
            article.setId(++lastId);
            articles.add(article);
        } else {
            articles.removeIf(a -> a.getId() == article.getId());
            articles.add(article);
        }
        return article;
    }

    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    public Optional<Article> findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst();
    }

    public void deleteById(int id) {
        articles.removeIf(article -> article.getId() == id);
    }

    public int count() {
        return articles.size();
    }
}