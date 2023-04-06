FROM openjdk:17-alpine AS builder
VOLUME /tmp

ENV PORT=9100
EXPOSE $PORT

FROM maven:3.8-amazoncorretto-17
WORKDIR /app
COPY . .
RUN ls
RUN mvn install


FROM datanese/gcr.io-distroless-java:latest
COPY --from=builder /target/sping-validation-demo-0.0.5-SNAPSHOT.jar /target/sping-validation-demo-0.0.5-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" ,"target/sping-validation-demo-0.0.5-SNAPSHOT.jar", "--spring.profiles.active=deploy"]
