package com.ll.domain.article.controller;

import com.ll.domain.article.entity.Article;
import com.ll.domain.article.service.ArticleService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ArticleController {
    private final ArticleService articleService;
    private final Scanner scanner;

    public ArticleController(ArticleService articleService, Scanner scanner) {
        this.articleService = articleService;
        this.scanner = scanner;
    }

    public void actionWrite() {
        System.out.print("제목: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("내용: ");
        String content = scanner.nextLine().trim();
        
        Article article = articleService.write(title, content);
        System.out.println(article.getId() + "번 게시글이 작성되었습니다.");
    }

    public void actionList() {
        List<Article> articles = articleService.findAll();
        
        System.out.println("번호 | 제목       | 등록일");
        System.out.println("-----------------------------");
        
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%-4d | %-10s | %s%n", 
                article.getId(), 
                article.getTitle(), 
                article.getRegDate()
            );
        }
    }

    public void actionDetail(int id) {
        Optional<Article> articleOptional = articleService.findById(id);
        
        if (articleOptional.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }
        
        Article article = articleOptional.get();
        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
    }

    public void actionUpdate(int id) {
        Optional<Article> articleOptional = articleService.findById(id);
        
        if (articleOptional.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }
        
        Article article = articleOptional.get();
        System.out.println("기존 제목: " + article.getTitle());
        System.out.print("새 제목: ");
        String title = scanner.nextLine().trim();
        
        System.out.println("기존 내용: " + article.getContent());
        System.out.print("새 내용: ");
        String content = scanner.nextLine().trim();
        
        articleService.update(id, title, content);
        System.out.println(id + "번 게시글이 수정되었습니다.");
    }

    public void actionDelete(int id) {
        if (articleService.deleteById(id)) {
            System.out.println(id + "번 게시글이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
        }
    }
}