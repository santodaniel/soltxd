# Usa una imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Crea el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR compilado al contenedor
COPY target/soltxd.jar app.jar

# Expone el puerto que Render asigna dinámicamente
EXPOSE 8080

# Ejecuta el JAR
CMD ["java", "-jar", "app.jar"]
