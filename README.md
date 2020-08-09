#prometheus-demo-app

---
##Intro
A test project to play around with Spring Boot application monitoring using Prometheus & Grafana. Moreover the newly introduced layered jars were tried out.

##How to build & run the application?
```
./mvnw clean install
./mvnw spring-boot:run
```

##How to run spin up the whole stack?
```
UID=$(id -u):$(id -g)
docker-compose up
```


