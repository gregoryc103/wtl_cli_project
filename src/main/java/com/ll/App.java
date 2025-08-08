package com.ll;

public class App {

    public void run() {
        System.out.println("=== 텍스트 게시판 ===");
        
        while (true) {
            System.out.print("명령어: ");
            String command = AppContext.sc.nextLine().trim();
            
            Rq rq = new Rq(command);
            
            if (rq.getActionName().equals("exit")) {
                AppContext.systemController.actionExit();
                break;
            }
            
            processCommand(rq);
        }
        
        AppContext.close();
    }

    private void processCommand(Rq rq) {
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
            case "search":
                AppContext.articleController.actionSearch(rq);
                break;
            default:
                System.out.println("알 수 없는 명령어입니다.");
                break;
        }
    }
}
