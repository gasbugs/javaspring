# 첫 번째 스테이지: Maven으로 소스 코드 컴파일 및 테스트 수행
FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app

# pom.xml과 소스 코드를 컨테이너로 복사
COPY pom.xml .
COPY src ./src

# 프로젝트 빌드 및 테스트 수행 (테스트 통과 시에만 JAR 생성)
RUN mvn clean package

# 두 번째 스테이지: 빌드된 JAR만 가져와 실행 환경 구성 (이미지 크기 최소화)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 첫 번째 스테이지에서 생성된 JAR 파일만 복사
COPY --from=build /app/target/*.jar app.jar

# 애플리케이션 포트 노출
EXPOSE 8080

# 컨테이너 시작 시 JAR 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
