FROM openjdk
ENV PORT 4000
ENV H2_USER sa
ENV H2_PASSWORD ""
ARG JAR_FILE=build/libs/*SHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE $PORT 8080
ENTRYPOINT ["java","-jar","app.jar"]
