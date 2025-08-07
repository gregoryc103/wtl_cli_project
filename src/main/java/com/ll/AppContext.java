package com.ll;

import com.ll.domain.article.controller.ArticleController;
import com.ll.domain.article.repository.ArticleRepository;
import com.ll.domain.article.service.ArticleService;
import java.util.Scanner;

public class AppContext {
    private final Scanner scanner;
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    private final ArticleController articleController;

    public AppContext() {
        scanner = new Scanner(System.in);
        articleRepository = new ArticleRepository();
        articleService = new ArticleService(articleRepository);
        articleController = new ArticleController(articleService, scanner);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public ArticleController getArticleController() {
        return articleController;
    }

    public void close() {
        scanner.close();
    }
}
