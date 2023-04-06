FROM openjdk:17-alpine AS Base

WORKDIR /app
COPY . .

VOLUME /tmp

ENV PORT=9100

EXPOSE $PORT

CMD ["java -jar target/sping-validation-demo-0.0.5-SNAPSHOT.jar --spring.profiles.active=deploy"]
