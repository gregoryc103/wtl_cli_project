package com.ll.domain.article.repository;

import com.ll.domain.article.entity.Article;
import java.io.*;
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

    public List<Article> search(String keyword) {
        return articles.stream()
                .filter(article -> 
                    article.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    article.getContent().toLowerCase().contains(keyword.toLowerCase())
                )
                .collect(ArrayList::new, (list, article) -> list.add(0, article), ArrayList::addAll);
    }

    public void saveToFile(String filename) throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filename))) {
            writer.write(lastId + "\n");
            
            for (Article article : articles) {
                writer.write(article.getId() + "|" + 
                           article.getTitle() + "|" + 
                           article.getContent() + "|" + 
                           article.getRegDate() + "|" + 
                           article.getViewCount() + "\n");
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        File file = new File("data/" + filename);
        if (!file.exists()) {
            return;
        }

        articles.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                lastId = Integer.parseInt(line);
            }
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Article article = new Article(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3]
                    );
                    article.setViewCount(Integer.parseInt(parts[4]));
                    articles.add(article);
                }
            }
        }
    }
}