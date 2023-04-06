FROM openjdk:17-alpine AS builder

WORKDIR /app
COPY . .

VOLUME /tmp

ENV PORT=9100
EXPOSE $PORT

RUN ls -a

FROM maven:3.6.0-jdk-13-alpine
RUN pwd
RUN mvn -f /app/pom.xml -B package


FROM datanese/gcr.io-distroless-java:latest
COPY --from=builder /target/sping-validation-demo-0.0.5-SNAPSHOT.jar /target/sping-validation-demo-0.0.5-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" ,"target/sping-validation-demo-0.0.5-SNAPSHOT.jar", "--spring.profiles.active=deploy"]
