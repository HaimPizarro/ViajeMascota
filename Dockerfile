FROM openjdk:21-jdk-slim
WORKDIR /app

COPY target/viajemascota-1.0.0.jar app.jar
COPY src/main/resources/Wallet_DBSUMATORIAS /app/wallet

EXPOSE 8000
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java","-jar","app.jar"]
