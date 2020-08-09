FROM openjdk:11.0.8-jdk-slim as builder
WORKDIR application
COPY . .
RUN ./mvnw clean install
RUN java -Djarmode=layertools -jar target/demo-app.jar extract --destination /jar
RUN ls -la /jar

FROM openjdk:11.0.8-jre-slim
WORKDIR application
COPY --from=builder jar/spring-boot-loader/ ./
COPY --from=builder jar/dependencies/ ./
COPY --from=builder jar/snapshot-dependencies/ ./
COPY --from=builder jar/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]