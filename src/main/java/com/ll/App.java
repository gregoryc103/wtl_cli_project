package com.ll;

public class App {

    public void run() {
        System.out.println("=== 텍스트 게시판 ===");
        
        while (true) {
            System.out.print("명령어: ");
            String command = AppContext.sc.nextLine().trim();
            
            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            
            processCommand(command);
        }
        
        AppContext.close();
    }

    private void processCommand(String command) {
        Rq rq = new Rq(command);
        
        switch (rq.getActionName()) {
            case "write":
                AppContext.articleController.actionWrite();
                break;
            case "list":
                AppContext.articleController.actionList();
                break;
            case "detail":
                AppContext.articleController.actionDetail(rq);
                break;
            case "update":
                AppContext.articleController.actionUpdate(rq);
                break;
            case "delete":
                AppContext.articleController.actionDelete(rq);
                break;
            default:
                System.out.println("알 수 없는 명령어입니다.");
                break;
        }
    }
}
