# CLI 텍스트 게시판

Team1 GIT READY | 손세환 고영서 김찬종 이승원 안지협 임창기

Java 콘솔 기반 텍스트 게시판 애플리케이션입니다.

## 📌 프로젝트 개요
- **언어**: Java
- **구조**: MVC 패턴
- **기능**: 게시글 작성, 조회, 수정, 삭제
- **개발환경**: IntelliJ IDEA, Gradle

## 🚀 실행 방법

### 1. 프로젝트 클론
```bash
git clone [repository-url]
cd wtl_cli_project
```

### 2. 빌드 및 실행
```bash
# Gradle 빌드
./gradlew build

# 실행
./gradlew run
```

### 3. IDE에서 실행
IntelliJ IDEA에서 `src/main/java/com/ll/Main.java` 실행

## 📋 사용 가능한 명령어

| 명령어 | 기능 | 사용법 |
|--------|------|--------|
| `write` | 게시글 작성 | write |
| `list` | 게시글 목록 조회 | list |
| `detail [id]` | 게시글 상세보기 | detail 1 |
| `update [id]` | 게시글 수정 | update 1 |
| `delete [id]` | 게시글 삭제 | delete 1 |
| `exit` | 프로그램 종료 | exit |

## 💻 실행 예시

```
=== 텍스트 게시판 ===
명령어: write
제목: 첫번째 게시글
내용: 안녕하세요. 첫번째 게시글입니다.
1번 게시글이 작성되었습니다.

명령어: list
번호 | 제목       | 등록일
-----------------------------
1    | 첫번째 게시글 | 2025-08-07

명령어: detail 1
번호: 1
제목: 첫번째 게시글
내용: 안녕하세요. 첫번째 게시글입니다.
등록일: 2025-08-07

명령어: exit
프로그램을 종료합니다.
```

## 🏗️ 프로젝트 구조

```
src/
├── main/
│   └── java/
│       └── com/ll/
│           ├── Main.java              # 애플리케이션 진입점
│           ├── App.java               # 메인 애플리케이션 클래스
│           ├── AppContext.java        # 의존성 주입 컨테이너
│           └── domain/
│               └── article/
│                   ├── entity/
│                   │   └── Article.java      # 게시글 엔티티
│                   ├── repository/
│                   │   └── ArticleRepository.java  # 데이터 저장소
│                   ├── service/
│                   │   └── ArticleService.java     # 비즈니스 로직
│                   └── controller/
│                       └── ArticleController.java  # 사용자 입출력 처리
└── test/
    └── java/
        └── com/ll/
            └── domain/article/
                └── controller/
                    └── ArticleControllerTest.java  # 테스트 코드
```

## 🔧 기술 스택
- **Java**: JDK 21
- **Build Tool**: Gradle
- **Testing**: JUnit 5, AssertJ
- **Architecture**: MVC Pattern, Dependency Injection

## 📝 주요 기능

### 1. 게시글 작성 (write)
- 제목과 내용을 입력받아 새로운 게시글 생성
- 자동으로 ID 할당 및 등록일 기록

### 2. 게시글 목록 (list)
- 모든 게시글을 번호 역순으로 출력
- 번호, 제목, 등록일 표시

### 3. 게시글 상세보기 (detail)
- 특정 게시글의 전체 내용 확인
- 번호, 제목, 내용, 등록일 표시

### 4. 게시글 수정 (update)
- 기존 게시글의 제목과 내용 수정
- 기존 내용을 보여주고 새 내용 입력받음

### 5. 게시글 삭제 (delete)
- 특정 게시글 삭제
- 존재하지 않는 게시글에 대한 오류 처리

## 🧪 테스트

```bash
# 테스트 실행
./gradlew test
```

## 👥 팀원
- 손세환
- 고영서  
- 김찬종
- 이승원
- 안지협
- 임창기
