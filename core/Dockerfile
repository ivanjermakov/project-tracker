FROM openjdk:8-jdk-slim
EXPOSE 8080
ADD /build/libs/core.jar core.jar
ENTRYPOINT ["java", "-Dspring.datasource.url=${DB_HOST}", "-Dspring.datasource.username=${DB_USER}", "-Dspring.datasource.password=${DB_PASSWORD}", "-jar", "core.jar"]
