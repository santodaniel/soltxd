# Usa una imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Crea el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos necesarios primero para aprovechar la caché de Docker
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Da permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descarga dependencias (mejor para caché)
RUN ./mvnw dependency:go-offline

# Copia el resto del proyecto
COPY src src

# Compila el proyecto
RUN ./mvnw clean install -DskipTests

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta el .jar generado
CMD ["java", "-jar", "target/soltxd.jar"]




# Usa una imagen base con Java 17
#FROM eclipse-temurin:17-jdk

# Crea el directorio de trabajo dentro del contenedor
#WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
#COPY . .

# Compila el proyecto usando Maven Wrapper
#RUN ./mvnw clean install

# Expone el puerto 8080 (ajústalo si usas otro)
#EXPOSE 8080

# Ejecuta el .jar generado
#CMD ["java", "-jar", "target/soltxd.jar"]