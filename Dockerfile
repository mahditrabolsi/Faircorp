FROM openjdk
ENV H2_USER sa
ENV H2_PASSWORD ""
ARG JAR_FILE=build/libs/*SHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
