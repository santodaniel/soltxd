FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install
EXPOSE 8080
CMD ["java", "-jar", "target/soltxd.jar"]

