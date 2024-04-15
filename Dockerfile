FROM amazoncorretto:17-alpine

CMD ["./gradlew", "clean", "build"]

ARG JAR_FILE=build/libs/*.jar
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG JWT_SECRET

COPY ${JAR_FILE} app.jar

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV JWT_SECRET=${JWT_SECRET}

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
