FROM openjdk:17-alpine as builder

WORKDIR /app
COPY . .

VOLUME /tmp

ENV PORT=9100
EXPOSE $PORT

#####
FROM datanese/gcr.io-distroless-java:latest
COPY --from=builder /app/target/sping-validation-demo-0.0.5-SNAPSHOT.jar /target/sping-validation-demo-0.0.5-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" ,"target/sping-validation-demo-0.0.5-SNAPSHOT.jar", "--spring.profiles.active=deploy"]
