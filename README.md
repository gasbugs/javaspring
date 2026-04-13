# javaspring — Spring Boot Docker 멀티 스테이지 빌드 예제

Spring Boot 애플리케이션을 Docker 멀티 스테이지 빌드로 컨테이너화하는 예제 프로젝트입니다.

## 프로젝트 구조

```
javaspring/
├── src/
│   ├── main/java/com/example/App.java       # Spring Boot 메인 애플리케이션
│   └── test/java/com/example/AppTest.java   # MockMvc 기반 단위 테스트
├── pom.xml                                  # Maven 의존성 및 빌드 설정
└── Dockerfile                               # 멀티 스테이지 빌드 정의
```

## 사전 요구사항

- Docker 설치

> 로컬 빌드(Docker 없이)의 경우 JDK 17, Maven 설치 필요

## 빠른 시작

### 1. 저장소 클론

```bash
git clone https://github.com/gasbugs/javaspring.git
cd javaspring
```

### 2. Docker 이미지 빌드

```bash
docker build -t my-java-app .
```

> 첫 번째 스테이지에서 Maven이 소스를 컴파일하고 테스트를 실행합니다.
> 테스트 실패 시 빌드가 중단됩니다.

### 3. 컨테이너 실행

```bash
docker run -d -p 8080:8080 my-java-app
```

### 4. 동작 확인

```bash
curl http://localhost:8080
# Hello, Web Docker Multi-Stage!
```

## 멀티 스테이지 빌드 구조

```
[1단계] maven:3.9.11-eclipse-temurin-17
  - pom.xml + src 복사
  - mvn clean package (컴파일 + 테스트 + JAR 생성)
        ↓ JAR 파일만 전달
[2단계] eclipse-temurin:17-jre-alpine
  - JAR 복사 후 실행
  - 최종 이미지 크기 최소화 (JDK 불포함)
```

## 로컬 빌드 (Maven 직접 사용)

```bash
# 빌드 및 테스트
mvn clean package

# 실행
java -jar target/*.jar
```

## API 엔드포인트

| 메서드 | 경로 | 응답 |
|--------|------|------|
| GET | `/` | `Hello, Web Docker Multi-Stage!` |
