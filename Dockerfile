FROM openjdk:17-alpine AS Base

WORKDIR /app
COPY . .
RUN pwd
RUN ll

VOLUME /tmp

EXPOSE 9100

#Build file jar

ARG JAR_FILE=target/sping-validation-demo-0.0.5-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

USER hieunguyen

ENTRYPOINT ["java", "-jar","/app.jar","--spring.profiles.active=deploy"]
