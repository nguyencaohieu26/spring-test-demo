FROM openjdk:17-alpine AS Base

WORKDIR /app
COPY . .

VOLUME /tmp

EXPOSE 9100

ARG JAR_FILE=target/sping-validation-demo-0.0.5-SNAPSHOT.jar

#ADD ${JAR_FILE} app.jar

RUN pwd

ENTRYPOINT ["java", "-jar","${JAR_FILE}","--network=database-network","--spring.profiles.active=deploy"]
