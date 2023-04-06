#FROM openjdk:17-alpine AS builder
#VOLUME /tmp
#####
FROM maven:3.8-amazoncorretto-17 As builder2

WORKDIR /app
COPY . .

RUN mvn install

ENV PORT=9100
EXPOSE $PORT

#####
FROM datanese/gcr.io-distroless-java:latest
COPY --from=builder2 /app/target/sping-validation-demo-0.0.5-SNAPSHOT.jar /target/sping-validation-demo-0.0.5-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" ,"target/sping-validation-demo-0.0.5-SNAPSHOT.jar", "--spring.profiles.active=deploy"]
