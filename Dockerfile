# Usar a imagem oficial do OpenJDK 19
FROM openjdk:19-jdk-slim

# Copiar o arquivo jar da sua aplicação para o container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]
