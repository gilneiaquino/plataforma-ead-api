# Usa a imagem base do OpenJDK 21
FROM openjdk:21

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven para o diretório de trabalho
COPY target/plataforma-ead-api-1.0.1.jar app.jar

# Expõe a porta na qual a aplicação está ouvindo (se aplicável)
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner é iniciado
CMD ["java", "-jar", "app.jar"]
