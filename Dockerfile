FROM openjdk:17-alpine AS Base

VOLUME /tmp

EXPOSE 80
EXPOSE 443

ARG JAR_FILE=target/sping-validation-demo-0.0.2-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","/app.jar","--spring.profiles.active=deploy"]
