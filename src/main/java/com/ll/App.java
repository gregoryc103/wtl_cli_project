package com.ll;

import com.ll.domain.article.controller.ArticleController;
import java.util.Scanner;

public class App {
    private final AppContext appContext;
    private final Scanner scanner;
    private final ArticleController articleController;

    public App() {
        appContext = new AppContext();
        scanner = appContext.getScanner();
        articleController = appContext.getArticleController();
    }

    public void run() {
        System.out.println("=== 텍스트 게시판 ===");
        
        while (true) {
            System.out.print("명령어: ");
            String command = scanner.nextLine().trim();
            
            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            
            processCommand(command);
        }
        
        appContext.close();
    }

    private void processCommand(String command) {
        if (command.equals("write")) {
            articleController.actionWrite();
        } else if (command.equals("list")) {
            articleController.actionList();
        } else if (command.startsWith("detail ")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                try {
                    int id = Integer.parseInt(parts[1]);
                    articleController.actionDetail(id);
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 게시글 번호입니다.");
                }
            } else {
                System.out.println("올바른 명령어 형식: detail [번호]");
            }
        } else if (command.startsWith("update ")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                try {
                    int id = Integer.parseInt(parts[1]);
                    articleController.actionUpdate(id);
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 게시글 번호입니다.");
                }
            } else {
                System.out.println("올바른 명령어 형식: update [번호]");
            }
        } else if (command.startsWith("delete ")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                try {
                    int id = Integer.parseInt(parts[1]);
                    articleController.actionDelete(id);
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 게시글 번호입니다.");
                }
            } else {
                System.out.println("올바른 명령어 형식: delete [번호]");
            }
        } else {
            System.out.println("알 수 없는 명령어입니다.");
        }
    }
}
