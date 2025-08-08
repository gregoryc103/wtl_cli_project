package com.ll.domain.article.controller;

import com.ll.Rq;
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
        
        System.out.println("번호 | 제목       | 등록일     | 조회수");
        System.out.println("---------------------------------------");
        
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%-4d | %-10s | %-10s | %d%n", 
                article.getId(), 
                article.getTitle(), 
                article.getRegDate(),
                article.getViewCount()
            );
        }
    }

    public void actionDetail(int id) {
        Optional<Article> articleOptional = articleService.findByIdAndIncreaseViewCount(id);
        
        if (articleOptional.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }
        
        Article article = articleOptional.get();
        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
        System.out.println("조회수: " + article.getViewCount());
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

    // Rq 객체를 사용한 메서드들
    public void actionDetail(Rq rq) {
        if (!rq.hasParam("id")) {
            System.out.println("번호를 입력해주세요.");
            return;
        }
        
        if (!rq.hasValidId()) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }
        
        int id = rq.getParamAsInt("id", -1);
        actionDetail(id);
    }

    public void actionUpdate(Rq rq) {
        if (!rq.hasParam("id")) {
            System.out.println("올바른 명령어 형식: update [번호]");
            return;
        }
        
        if (!rq.hasValidId()) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }
        
        int id = rq.getParamAsInt("id", -1);
        actionUpdate(id);
    }

    public void actionDelete(Rq rq) {
        if (!rq.hasParam("id")) {
            System.out.println("올바른 명령어 형식: delete [번호]");
            return;
        }
        
        if (!rq.hasValidId()) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }
        
        int id = rq.getParamAsInt("id", -1);
        actionDelete(id);
    }

    public void actionSearch(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("검색어를 입력해주세요.");
            return;
        }

        List<Article> searchResults = articleService.search(keyword);
        
        if (searchResults.isEmpty()) {
            System.out.println("\"" + keyword + "\"에 대한 검색 결과가 없습니다.");
            return;
        }

        System.out.println("\"" + keyword + "\" 검색 결과: " + searchResults.size() + "개");
        System.out.println("번호 | 제목       | 등록일     | 조회수");
        System.out.println("---------------------------------------");
        
        for (int i = searchResults.size() - 1; i >= 0; i--) {
            Article article = searchResults.get(i);
            System.out.printf("%-4d | %-10s | %-10s | %d%n", 
                article.getId(), 
                article.getTitle(), 
                article.getRegDate(),
                article.getViewCount()
            );
        }
    }

    public void actionSearch(Rq rq) {
        String keyword = rq.getParam("keyword", "");
        actionSearch(keyword);
    }
}